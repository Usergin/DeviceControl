package com.shiz.repository.db.dao;

import com.shiz.config.HibernateSessionFactory;
import com.shiz.entity.DeviceEntity;
import com.shiz.entity.MessageEntity;
import com.shiz.entity.MessageEntity_;
import com.shiz.model.data.event.Message;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by OldMan on 10.05.2017.
 */
@Component
public class MessageDaoImpl implements MessageDao {
    private SessionFactory sessionFactory;

    public MessageDaoImpl() {
        sessionFactory = HibernateSessionFactory.getSessionFactory();
    }

    @Override
    public void addMessageList(int deviceId, List<Message> messageList) throws Exception {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            DeviceEntity deviceEntity = ((DeviceEntity) session
                    .getNamedQuery(DeviceEntity.NamedQuery.DEVICE_FIND_BY_ID)
                    .setParameter("device_id", deviceId)
                    .uniqueResult());
            for (Message message : messageList) {
                CriteriaBuilder cb = session.getCriteriaBuilder();
                CriteriaQuery<MessageEntity> criteria = cb.createQuery(MessageEntity.class);
                Root<MessageEntity> userRoot = criteria.from(MessageEntity.class);
                Predicate p = cb.and(cb.equal(userRoot.get(MessageEntity_.date), message.getDate())
                        , (cb.equal(userRoot.get(MessageEntity_.messageByDeviceId), deviceId)));
                criteria.where(p);

                if (session.createQuery(criteria).getSingleResult() == null) {
                    MessageEntity messageEntity = new MessageEntity();
                    messageEntity.setData(message.getData());
                    messageEntity.setDate(new java.sql.Timestamp(message.getDate().getTime()));
                    messageEntity.setNumber(message.getNumber());
                    messageEntity.setTypeEventId(message.getType());
                    messageEntity.setMessageByDeviceId(deviceEntity);
                    deviceEntity.addMessageByDeviceId(messageEntity);
                    session.save(messageEntity);
                }
                session.flush();
                session.clear();
                session.saveOrUpdate(deviceEntity);
            }
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public List<Message> getMessageEntityList(int deviceId) throws Exception {
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            DeviceEntity deviceEntity = (DeviceEntity) session
                    .getNamedQuery(DeviceEntity.NamedQuery.DEVICE_FIND_BY_ID)
                    .setParameter("device_id", deviceId)
                    .getSingleResult();
            List<MessageEntity> messageEntityList = deviceEntity.getMessageByDeviceId();
            List<Message> messageList = new ArrayList<>();
            for (MessageEntity messageEntity : messageEntityList) {
                Message location = new Message();
                location.setData(messageEntity.getData());
                location.setDate(messageEntity.getDate());
                location.setNumber(messageEntity.getNumber());
                location.setType(messageEntity.getTypeEventId());
                messageList.add(location);
            }
            return messageList;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
