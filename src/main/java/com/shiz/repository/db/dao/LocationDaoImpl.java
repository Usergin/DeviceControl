package com.shiz.repository.db.dao;

import com.shiz.config.HibernateSessionFactory;
import com.shiz.entity.*;
import com.shiz.model.data.event.Location;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
            for (Iterator<Location> iterator = locationList.iterator(); iterator.hasNext(); ) {
                Location location = iterator.next();
                LocationEntity locationEntity = new LocationEntity();
                locationEntity.setAccuracy(location.getAccuracy());
                locationEntity.setDate(new java.sql.Timestamp(location.getDate().getTime()));
                locationEntity.setLatitude(location.getLatitude());
                locationEntity.setLongitude(location.getLongitude());
                locationEntity.setMethod(location.getMethod());
                locationEntity.setLocationByDeviceId(deviceEntity);
                deviceEntity.addLocationByDeviceId(locationEntity);
                if (!iterator.hasNext()) {
                    deviceEntity.setLatitude(location.getLatitude());
                    deviceEntity.setLongitude(location.getLongitude());
                }
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

    @Override
    public List<Location> getLocationListByDate(int deviceId, String date) throws Exception {
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
//            DeviceEntity deviceEntity = (DeviceEntity) session
//                    .getNamedQuery(DeviceEntity.NamedQuery.DEVICE_FIND_BY_ID)
//                    .setParameter("device_id", deviceId)
//                    .getSingleResult();
//            List<LocationEntity> locationEntities = deviceEntity.getLocationByDeviceId();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<LocationEntity> criteria = cb.createQuery(LocationEntity.class);
            Root<LocationEntity> locationEntityRoot = criteria.from(LocationEntity.class);
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-YYYY");
            Date minDate = formatter.parse(date);
            long minDateTime = minDate.getTime();
            Date maxDate = new Date(minDate.getTime() + TimeUnit.DAYS.toMillis(1));
            long maxDateTime = maxDate.getTime();
            Predicate p = cb.and(cb.between(locationEntityRoot.get(LocationEntity_.date), minDate, maxDate)
                    ,(cb.equal(locationEntityRoot.get(LocationEntity_.locationByDeviceId),deviceId)));
            criteria.where(p);
            List<LocationEntity> locations = session.createQuery(criteria).getResultList();

            List<Location> locationList = new ArrayList<>();
            for (LocationEntity locationEntity : locations) {
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
