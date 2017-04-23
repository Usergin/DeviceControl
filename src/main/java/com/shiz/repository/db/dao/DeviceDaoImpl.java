package com.shiz.repository.db.dao;

import com.shiz.config.HibernateSessionFactory;
import com.shiz.entity.DeviceEntity;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by oldman on 22.04.17.
 */
@Component
public class DeviceDaoImpl implements DeviceDao {

    private SessionFactory sessionFactory;

        public DeviceDaoImpl() {
        sessionFactory = HibernateSessionFactory.getSessionFactory();
    }

    protected Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public DeviceEntity addDevice(DeviceEntity deviceEntity) throws SQLException, Exception {
        getSession().saveOrUpdate(deviceEntity);
        return deviceEntity;
    }

    @Override
    public void addDevicesList(Collection<DeviceEntity> deviceEntity) throws SQLException, Exception {
        getSession().saveOrUpdate(deviceEntity);
    }

    @Override
    public Collection<DeviceEntity> getAllDevice() throws SQLException, Exception {
        return getSession().createQuery("from DeviceEntity t").list();
    }

    @Override
    public void deleteAllDevice(Collection<DeviceEntity> deviceEntityCollection) throws SQLException, Exception {
        sessionFactory.getCurrentSession().delete(deviceEntityCollection);
    }

    @Override
    public DeviceEntity getDeviceByDeviceId(int deviceId) throws SQLException, Exception {
        DeviceEntity deviceEntity = null;
        try {
            deviceEntity = (DeviceEntity) getSession()
                    .createQuery("from DeviceEntity where deviceId = :device_id")
                    .setParameter("device_id", deviceId).getSingleResult();
            return deviceEntity;
        } catch (NoResultException nre) {
            return null;
        } catch (NonUniqueResultException nure) {
            return null;
        }
    }

    @Override
    public int getDeviceIdByImei(String imei) throws SQLException, Exception {
        DeviceEntity deviceEntity = null;
        try {
            deviceEntity = (DeviceEntity) getSession()
                    .createQuery("from DeviceEntity where imei like :imei")
                    .setParameter("imei", "%" + imei + "%").getSingleResult();
            return deviceEntity.getDeviceId();
        } catch (NoResultException nre) {
            return -1;
        } catch (NonUniqueResultException nure) {
            return -1;
        }
    }
}
