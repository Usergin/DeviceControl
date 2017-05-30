package com.shiz.repository.db.dao;

import com.shiz.config.HibernateSessionFactory;
import com.shiz.entity.CallEntity;
import com.shiz.entity.DeviceEntity;
import com.shiz.entity.MessageEntity;
import com.shiz.model.data.event.Message;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

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
                Criteria userCriteria = session.createCriteria(CallEntity.class);
                userCriteria.add(Restrictions.eq("date", message.getDate()));
                if (userCriteria.uniqueResult() == null) {
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
