package com.shiz.repository.db.dao;

import com.shiz.config.HibernateSessionFactory;
import com.shiz.entity.AppEntity;
import com.shiz.entity.BatteryStatusEntity;
import com.shiz.entity.DeviceEntity;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

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
            return -1;
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
            return null;
        }
//        catch (Exception e) {
//            e.printStackTrace();
//            throw new ErrorExceptionResponse(0, "Error on server: " + e.getMessage());
//        }
    }

    @Override
    public boolean deleteAllDevice() throws SQLException, Exception {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            String hql = "delete from DeviceEntity";
            session.createNativeQuery(hql);
            return true;
        } catch (NoResultException | NonUniqueResultException nre) {
            return false;
        }
    }


    @Override
    public void deleteDeviceById(int deviceId) throws SQLException, Exception {

    }

    @Override
    public int addAppList(int deviceId, List<AppEntity> appEntities) throws SQLException, Exception {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            DeviceEntity deviceEntity = ((DeviceEntity) session
                    .getNamedQuery(DeviceEntity.NamedQuery.DEVICE_FIND_BY_ID)
                    .setParameter("device_id", deviceId)
                    .uniqueResult());
            for (AppEntity installApp : appEntities) {
                List<AppEntity> appList = deviceEntity.getAppByDeviceId();
                for (AppEntity app : appList)
                    if (app.getName().equals(installApp.getName())) {
                        System.out.print("getName  " + app.getName());
                        app.setInfo(installApp.getInfo());
                        app.setDateInstalled(installApp.getDateInstalled());
                        app.setAppByDeviceId(deviceEntity);
                        appList.remove(app);
                    }
                installApp.setAppByDeviceId(deviceEntity);
                deviceEntity.addApp(installApp);
                session.save(installApp);
            }
            session.saveOrUpdate(deviceEntity);
            session.getTransaction().commit();
            return deviceId;
        } catch (NoResultException | NonUniqueResultException nre) {
            System.out.print("NoResultException  " + nre);
            return 0;

        } catch (Exception e) {
            System.out.print("Exception  " + e);
            return -1;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

    }

    @Override
    public int addBatteryStatus(int deviceId, BatteryStatusEntity batteryStatusEntity) throws SQLException, Exception {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            DeviceEntity deviceEntity = ((DeviceEntity) session
                    .getNamedQuery(DeviceEntity.NamedQuery.DEVICE_FIND_BY_ID)
                    .setParameter("device_id", deviceId)
                    .uniqueResult());
            batteryStatusEntity.setBatteryByDeviceId(deviceEntity);
            deviceEntity.addBatteryStatusByDeviceId(batteryStatusEntity);
            session.save(batteryStatusEntity);
            session.saveOrUpdate(deviceEntity);
            session.getTransaction().commit();
            return deviceId;
        } catch (NoResultException | NonUniqueResultException nre) {
            System.out.print("NoResultException  " + nre);
            return 0;

        } catch (Exception e) {
            System.out.print("Exception  " + e);
            return -1;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

    }

}
