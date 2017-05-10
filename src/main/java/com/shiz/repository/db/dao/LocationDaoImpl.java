package com.shiz.repository.db.dao;

import com.shiz.config.HibernateSessionFactory;
import com.shiz.entity.DeviceEntity;
import com.shiz.entity.LocationEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by OldMan on 10.05.2017.
 */
@Component
public class LocationDaoImpl implements LocationDao {
    private SessionFactory sessionFactory;

    public LocationDaoImpl() {
        sessionFactory = HibernateSessionFactory.getSessionFactory();
    }


    @Override
    public void addLocation(int deviceId, List<LocationEntity> locationEntities) throws Exception {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            DeviceEntity deviceEntity = ((DeviceEntity) session
                    .getNamedQuery(DeviceEntity.NamedQuery.DEVICE_FIND_BY_ID)
                    .setParameter("device_id", deviceId)
                    .uniqueResult());
            for (LocationEntity locationEntity : locationEntities) {
                locationEntity.setLocationByDeviceId(deviceEntity);
                deviceEntity.addLocationByDeviceId(locationEntity);
                session.save(locationEntity);
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
    public List<LocationEntity> getLocationEntityList(int deviceId) throws Exception {
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            DeviceEntity deviceEntity = (DeviceEntity) session
                    .getNamedQuery(DeviceEntity.NamedQuery.DEVICE_FIND_BY_ID)
                    .setParameter("device_id", deviceId)
                    .getSingleResult();

            return deviceEntity.getLocationByDeviceId();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
