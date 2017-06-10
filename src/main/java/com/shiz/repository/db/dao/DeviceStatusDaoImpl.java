package com.shiz.repository.db.dao;

import com.shiz.config.HibernateSessionFactory;
import com.shiz.entity.DeviceEntity;
import com.shiz.entity.DeviceStatusEntity;
import com.shiz.model.data.event.DeviceEvent;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by OldMan on 10.05.2017.
 */
@Component
public class DeviceStatusDaoImpl implements DeviceStatusDao {
    private SessionFactory sessionFactory;

    public DeviceStatusDaoImpl() {
        sessionFactory = HibernateSessionFactory.getSessionFactory();
    }


    @Override
    public void addDeviceStatus(int deviceId, List<DeviceEvent> deviceEventList) throws Exception {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            DeviceEntity deviceEntity = ((DeviceEntity) session
                    .getNamedQuery(DeviceEntity.NamedQuery.DEVICE_FIND_BY_ID)
                    .setParameter("device_id", deviceId)
                    .uniqueResult());
            for (DeviceEvent deviceEvent : deviceEventList) {
                DeviceStatusEntity deviceStatusEntity = new DeviceStatusEntity();
                deviceStatusEntity.setDate(new java.sql.Timestamp(deviceEvent.getDate().getTime()));
                deviceStatusEntity.setStatus(deviceEvent.getStatus());
                deviceStatusEntity.setDeviceStatusByDeviceId(deviceEntity);
                deviceEntity.addDeviceStatusByDeviceId(deviceStatusEntity);
                session.save(deviceStatusEntity);
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
    public List<DeviceEvent> getDeviceStatusList(int deviceId) throws Exception {
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            DeviceEntity deviceEntity = (DeviceEntity) session
                    .getNamedQuery(DeviceEntity.NamedQuery.DEVICE_FIND_BY_ID)
                    .setParameter("device_id", deviceId)
                    .getSingleResult();
            List<DeviceStatusEntity> deviceStatusEntityList = deviceEntity.getDeviceStatusByDeviceId();
            List<DeviceEvent> deviceEvents = new ArrayList<>();
            for (DeviceStatusEntity deviceStatusEntity : deviceStatusEntityList) {
                DeviceEvent deviceEvent = new DeviceEvent();
                deviceEvent.setDate(deviceStatusEntity.getDate());
                deviceEvent.setStatus(deviceStatusEntity.getStatus());
                deviceEvents.add(deviceEvent);
            }
            return deviceEvents;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
