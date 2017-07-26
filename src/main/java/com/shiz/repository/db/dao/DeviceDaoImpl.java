package com.shiz.repository.db.dao;

import com.shiz.config.HibernateSessionFactory;
import com.shiz.entity.ContactBookEntity;
import com.shiz.entity.ContactBookEntity_;
import com.shiz.entity.DeviceEntity;
import com.shiz.entity.DeviceEntity_;
import com.shiz.model.Device;
import com.shiz.repository.db.DBServiceImpl;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oldman on 22.04.17.
 */
@Component
public class DeviceDaoImpl implements DeviceDao {
    private Logger logger = LoggerFactory.getLogger(DeviceDaoImpl.class);

    private SessionFactory sessionFactory;

    public DeviceDaoImpl() {
        sessionFactory = HibernateSessionFactory.getSessionFactory();
    }

    @Override
    public int addDevice(DeviceEntity deviceEntity) throws Exception {
        Session session = null;
        int deviceId = -1;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            deviceId = ((DeviceEntity) session
                    .getNamedQuery(DeviceEntity.NamedQuery.DEVICE_FIND_BY_IMEI)
                    .setParameter("imei", deviceEntity.getImei())
                    .getSingleResult()).getDeviceId();
            return deviceId;
        } catch (NoResultException | NonUniqueResultException nre) {
            if(session!=null) {
                session.save(deviceEntity);
                session.getTransaction().commit();
            }
            return deviceEntity.getDeviceId();
        } catch (Exception e) {
            return -1;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public Device getDeviceByDeviceId(int deviceId) throws SQLException, Exception {
        DeviceEntity deviceEntity = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            deviceEntity = (DeviceEntity) session
//                    .createQuery("from DeviceEntity where deviceId = :device_id")
                    .getNamedQuery(DeviceEntity.NamedQuery.DEVICE_FIND_BY_ID)
                    .setParameter("device_id", deviceId).getSingleResult();
            logger.info("getDeviceByDeviceId: ", deviceEntity.getDeviceId());
//            CriteriaBuilder cb = session.getCriteriaBuilder();
//            CriteriaQuery<DeviceEntity> criteria = cb.createQuery(DeviceEntity.class);
//            Root<DeviceEntity> deviceEntityRoot = criteria.from(DeviceEntity.class);
//            Predicate p = cb.equal(deviceEntityRoot.get(DeviceEntity_.deviceId), deviceId);
//            criteria.where(p);
//
//            deviceEntity = session.createQuery(criteria).getSingleResult();
            logger.info("getDeviceByDeviceId: ", deviceEntity.getId());

            return Device.newBuilder()
                    .imei(deviceEntity.getImei())
                    .id(deviceEntity.getDeviceId())
                    .model(deviceEntity.getModel())
                    .latitude(deviceEntity.getLatitude())
                    .longitude(deviceEntity.getLongitude())
                    .version_os(deviceEntity.getVersionOs())
                    .build();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public Device getDeviceIdByImei(String imei) throws SQLException, Exception {
        Session session = sessionFactory.getCurrentSession();
        try {
            DeviceEntity deviceEntity = (DeviceEntity) session
                    .getNamedQuery(DeviceEntity.NamedQuery.DEVICE_FIND_BY_IMEI)
                    .setParameter("imei", imei)
                    .getSingleResult();
            return   Device.newBuilder()
                    .imei(deviceEntity.getImei())
                    .id(deviceEntity.getDeviceId())
                    .model(deviceEntity.getModel())
                    .latitude(deviceEntity.getLatitude())
                    .longitude(deviceEntity.getLongitude())
                    .version_os(deviceEntity.getVersionOs())
                    .build();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public List<Device> getAllDevice() throws SQLException, Exception {
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            List<DeviceEntity> deviceEntityList = session
                    .getNamedQuery(DeviceEntity.NamedQuery.DEVICE_FIND_ALL)
                    .getResultList();
            List<Device> deviceList = new ArrayList<>();
            if (deviceEntityList != null && deviceEntityList.size() != 0)
                for (DeviceEntity deviceEntity : deviceEntityList) {
                    Device device = Device.newBuilder()
                            .imei(deviceEntity.getImei())
                            .id(deviceEntity.getDeviceId())
                            .model(deviceEntity.getModel())
                            .latitude(deviceEntity.getLatitude())
                            .longitude(deviceEntity.getLongitude())
                            .version_os(deviceEntity.getVersionOs())
                            .build();
                    deviceList.add(device);
                }
            return deviceList;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void deleteAllDevice() throws SQLException, Exception {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.createQuery("delete from DeviceEntity").executeUpdate();
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }


    @Override
    public void deleteDeviceById(int deviceId) throws SQLException, Exception {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            DeviceEntity deviceEntity = ((DeviceEntity) session
                    .getNamedQuery(DeviceEntity.NamedQuery.DEVICE_FIND_BY_ID)
                    .setParameter("device_id", deviceId)
                    .uniqueResult());
            session.remove(deviceEntity);
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
