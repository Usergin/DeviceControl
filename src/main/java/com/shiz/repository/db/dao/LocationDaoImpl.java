package com.shiz.repository.db.dao;

import com.shiz.config.HibernateSessionFactory;
import com.shiz.entity.DeviceEntity;
import com.shiz.entity.LocationEntity;
import com.shiz.model.data.event.Location;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
    public void addLocationList(int deviceId, List<Location> locationList) throws Exception {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            DeviceEntity deviceEntity = ((DeviceEntity) session
                    .getNamedQuery(DeviceEntity.NamedQuery.DEVICE_FIND_BY_ID)
                    .setParameter("device_id", deviceId)
                    .uniqueResult());
            for (Location location : locationList) {
                LocationEntity locationEntity = new LocationEntity();
                locationEntity.setAccuracy(location.getAccuracy());
                locationEntity.setDate(new java.sql.Timestamp(location.getDate().getTime()));
                locationEntity.setLatitude(location.getLatitude());
                locationEntity.setLongitude(location.getLongitude());
                locationEntity.setMethod(location.getMethod());
                locationEntity.setLocationByDeviceId(deviceEntity);
                deviceEntity.addLocationByDeviceId(locationEntity);
                session.save(locationEntity);
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
    public List<Location> getLocationList(int deviceId) throws Exception {
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            DeviceEntity deviceEntity = (DeviceEntity) session
                    .getNamedQuery(DeviceEntity.NamedQuery.DEVICE_FIND_BY_ID)
                    .setParameter("device_id", deviceId)
                    .getSingleResult();
            List<LocationEntity> locationEntities = deviceEntity.getLocationByDeviceId();
            List<Location> locationList = new ArrayList<>();
            for (LocationEntity locationEntity : locationEntities) {
                Location location = new Location();
                location.setAccuracy(locationEntity.getAccuracy());
                location.setDate(locationEntity.getDate());
                location.setLatitude(locationEntity.getLatitude());
                location.setLongitude(locationEntity.getLongitude());
                location.setMethod(locationEntity.getMethod());
                locationList.add(location);
            }
            return locationList;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
