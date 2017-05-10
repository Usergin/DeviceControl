package com.shiz.repository.db.dao;

import com.shiz.config.HibernateSessionFactory;
import com.shiz.entity.BatteryEntity;
import com.shiz.entity.DeviceEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by OldMan on 09.05.2017.
 */
@Component
public class BatteryStatusDaoImpl implements BatteryStatusDao {
    private SessionFactory sessionFactory;

    public BatteryStatusDaoImpl() {
        sessionFactory = HibernateSessionFactory.getSessionFactory();
    }


    @Override
    public void addBatteryStatus(int deviceId, List<BatteryEntity> batteryEntities) throws Exception {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            DeviceEntity deviceEntity = ((DeviceEntity) session
                    .getNamedQuery(DeviceEntity.NamedQuery.DEVICE_FIND_BY_ID)
                    .setParameter("device_id", deviceId)
                    .uniqueResult());
            for (BatteryEntity batteryEntity : batteryEntities) {
                batteryEntity.setBatteryByDeviceId(deviceEntity);
                deviceEntity.addBatteryStatusByDeviceId(batteryEntity);
                session.save(batteryEntity);
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
    public List<BatteryEntity> getBatteryEntityList(int deviceId) throws Exception {
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            DeviceEntity deviceEntity = (DeviceEntity) session
                    .getNamedQuery(DeviceEntity.NamedQuery.DEVICE_FIND_BY_ID)
                    .setParameter("device_id", deviceId)
                    .getSingleResult();

            return deviceEntity.getBatteryStatusByDeviceId();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
