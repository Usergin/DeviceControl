package com.shiz.repository.db.dao;

import com.shiz.config.HibernateSessionFactory;
import com.shiz.entity.DeviceEntity;
import com.shiz.entity.MessageEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

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
    public void addMessageList(int deviceId, List<MessageEntity> newMessageEntities) throws Exception {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            DeviceEntity deviceEntity = ((DeviceEntity) session
                    .getNamedQuery(DeviceEntity.NamedQuery.DEVICE_FIND_BY_ID)
                    .setParameter("device_id", deviceId)
                    .uniqueResult());
            for (MessageEntity messageEntity : newMessageEntities) {
                List<MessageEntity> messageList = deviceEntity.getMessageByDeviceId();
                for (MessageEntity message : messageList)
                    if (message.getDate().equals(messageEntity.getDate())) {
                        newMessageEntities.remove(message);
                    } else {
                        messageEntity.setMessageByDeviceId(deviceEntity);
                        deviceEntity.addMessageByDeviceId(messageEntity);
                        session.save(messageEntity);
                    }
            }
            session.saveOrUpdate(deviceEntity);
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public List<MessageEntity> getMessageEntityList(int deviceId) throws Exception {
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            DeviceEntity deviceEntity = (DeviceEntity) session
                    .getNamedQuery(DeviceEntity.NamedQuery.DEVICE_FIND_BY_ID)
                    .setParameter("device_id", deviceId)
                    .getSingleResult();

            return deviceEntity.getMessageByDeviceId();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
