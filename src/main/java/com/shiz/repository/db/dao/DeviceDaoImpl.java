package com.shiz.repository.db.dao;

import com.shiz.config.HibernateSessionFactory;
import com.shiz.entity.AppEntity;
import com.shiz.entity.DeviceEntity;
import com.shiz.model.data.event.InstallApp;
import com.shiz.repository.exception.ErrorExceptionResponse;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by oldman on 22.04.17.
 */
@Component
public class DeviceDaoImpl implements DeviceDao {

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
            session.save(deviceEntity);
            session.getTransaction().commit();
            return deviceEntity.getDeviceId();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ErrorExceptionResponse(0, "Error on server: " + e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public DeviceEntity getDeviceByDeviceId(int deviceId) throws SQLException, Exception {
        DeviceEntity deviceEntity = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            deviceEntity = (DeviceEntity) session
                    .createQuery("from DeviceEntity where deviceId = :device_id")
                    .setParameter("device_id", deviceId).getSingleResult();
            return deviceEntity;
        } catch (NoResultException nre) {
            return null;
        } catch (NonUniqueResultException nure) {
            return null;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public int getDeviceIdByImei(String imei) throws SQLException, Exception {
        Session session = sessionFactory.getCurrentSession();
        try {
            DeviceEntity deviceEntity = (DeviceEntity) session
                    .getNamedQuery(DeviceEntity.NamedQuery.DEVICE_FIND_BY_IMEI)
                    .setParameter("imei", imei)
                    .getSingleResult();
            return deviceEntity.getDeviceId();
        } catch (NoResultException nre) {
            return -1;
        } catch (NonUniqueResultException nure) {
            return -1;
        }
    }

    @Override
    public List getAllDevice() throws SQLException, Exception {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            return session
                    .getNamedQuery(DeviceEntity.NamedQuery.DEVICE_FIND_ALL)
                    .getResultList();
        } catch (NoResultException | NonUniqueResultException nre) {
            throw new ErrorExceptionResponse(0, "Not found device");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ErrorExceptionResponse(0, "Error on server: " + e.getMessage());
        }
    }

    @Override
    public void deleteAllDevice(List<DeviceEntity> deviceEntityCollection) throws SQLException, Exception {
        sessionFactory.getCurrentSession().delete(deviceEntityCollection);
    }


    @Override
    public void deleteDeviceById(int deviceId) throws SQLException, Exception {

    }

    @Override
    public int addAppList(int deviceId, List<AppEntity> appEntities) throws SQLException, Exception {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            System.out.print("openSession ");
            session.beginTransaction();
            DeviceEntity deviceEntity = ((DeviceEntity) session
                    .getNamedQuery(DeviceEntity.NamedQuery.DEVICE_FIND_BY_ID)
                    .setParameter("device_id", deviceId)
                    .uniqueResult());
//            AppEntity appEntity = new AppEntity();
//            appEntity.setName("sadsa");
////            appEntity.setDateInstalled(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
//            appEntity.setInfo("asdasda");
////            appEntity.setDeviceId(deviceEntity.getDeviceId());
//            appEntity.setAppByDeviceId(deviceEntity);

//            deviceEntity.getAppByDeviceId().add(appEntity);
            for (int i = 0; i < appEntities.size(); i++) {
                AppEntity installApp = appEntities.get(i);
                installApp.setAppByDeviceId(deviceEntity);
                appEntities.set(i, installApp);
                session.save(installApp);
            }
//            System.out.print("device_id " + deviceEntity.getId() + " app " + deviceEntity.getAppByDeviceId());
            deviceEntity.getAppByDeviceId().addAll(appEntities);
//            System.out.print("addApp ");
            session.merge(deviceEntity);
            session.getTransaction().commit();
            return deviceId;
        } catch (NoResultException | NonUniqueResultException nre) {
            throw new ErrorExceptionResponse(0, "Not found device");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ErrorExceptionResponse(0, "Error on server: " + e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

    }

}
