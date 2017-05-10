package com.shiz.repository.db.dao;

import com.shiz.config.HibernateSessionFactory;
import com.shiz.entity.DeviceEntity;
import com.shiz.entity.DeviceStatusEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by OldMan on 10.05.2017.
 */
@Component
public class DeviceStatusImpl implements DeviceStatusDao {
    private SessionFactory sessionFactory;

    public DeviceStatusImpl() {
        sessionFactory = HibernateSessionFactory.getSessionFactory();
    }


    @Override
    public void addDeviceStatus(int deviceId, DeviceStatusEntity deviceStatusEntity) throws Exception {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            DeviceEntity deviceEntity = ((DeviceEntity) session
                    .getNamedQuery(DeviceEntity.NamedQuery.DEVICE_FIND_BY_ID)
                    .setParameter("device_id", deviceId)
                    .uniqueResult());
            deviceStatusEntity.setDeviceStatusByDeviceId(deviceEntity);
            deviceEntity.addDeviceStatusByDeviceId(deviceStatusEntity);
            session.save(deviceStatusEntity);
            session.saveOrUpdate(deviceEntity);
            session.getTransaction().commit();
         } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public List<DeviceStatusEntity> getDeviceStatusEntityList(int deviceId) throws Exception {
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            DeviceEntity deviceEntity = (DeviceEntity) session
                    .getNamedQuery(DeviceEntity.NamedQuery.DEVICE_FIND_BY_ID)
                    .setParameter("device_id", deviceId)
                    .getSingleResult();

            return deviceEntity.getDeviceStatusByDeviceId();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
