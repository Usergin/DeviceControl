package com.shiz.repository.db.dao;

import com.shiz.config.HibernateSessionFactory;
import com.shiz.entity.BatteryEntity;
import com.shiz.entity.DeviceEntity;
import com.shiz.model.data.event.BatteryEvent;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
    public void addBatteryStatus(int deviceId, List<BatteryEvent> batteryEvents) throws Exception {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            DeviceEntity deviceEntity = ((DeviceEntity) session
                    .getNamedQuery(DeviceEntity.NamedQuery.DEVICE_FIND_BY_ID)
                    .setParameter("device_id", deviceId)
                    .uniqueResult());
            for (BatteryEvent batteryEvent : batteryEvents) {
                BatteryEntity batteryStatusEntity = new BatteryEntity();
                batteryStatusEntity.setBatteryStatus(batteryEvent.getBatteryStatus());
                batteryStatusEntity.setDate(new java.sql.Timestamp(batteryEvent.getDate().getTime()));
                batteryStatusEntity.setLevel(batteryEvent.getLevel());
                batteryStatusEntity.setStatus(batteryEvent.getStatus());
                batteryStatusEntity.setTypeCharging(batteryEvent.getTypeCharging());
                batteryStatusEntity.setBatteryByDeviceId(deviceEntity);
                deviceEntity.addBatteryStatusByDeviceId(batteryStatusEntity);
                session.save(batteryStatusEntity);
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
    public List<BatteryEvent> getBatteryEventList(int deviceId) throws Exception {
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            DeviceEntity deviceEntity = (DeviceEntity) session
                    .getNamedQuery(DeviceEntity.NamedQuery.DEVICE_FIND_BY_ID)
                    .setParameter("device_id", deviceId)
                    .getSingleResult();
            List<BatteryEntity> batteryEntityList = deviceEntity.getBatteryStatusByDeviceId();
            List<BatteryEvent> batteryEvents = new ArrayList<>();
            for (BatteryEntity batteryEntity : batteryEntityList) {
                BatteryEvent batteryEvent = new BatteryEvent();
                batteryEvent.setStatus(batteryEntity.getStatus());
                batteryEvent.setDate(batteryEntity.getDate());
                batteryEvent.setBatteryStatus(batteryEntity.getBatteryStatus());
                batteryEvent.setLevel(batteryEntity.getLevel());
                batteryEvent.setTypeCharging(batteryEntity.getTypeCharging());
                batteryEvents.add(batteryEvent);
            }
            return batteryEvents;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
