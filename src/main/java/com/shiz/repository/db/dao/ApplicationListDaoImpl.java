package com.shiz.repository.db.dao;

import com.shiz.config.HibernateSessionFactory;
import com.shiz.entity.AppEntity;
import com.shiz.entity.DeviceEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by oldman on 22.04.17.
 */
@Component
public class ApplicationListDaoImpl implements ApplicationDao {
    private SessionFactory sessionFactory;

    public ApplicationListDaoImpl() {
        sessionFactory = HibernateSessionFactory.getSessionFactory();
    }

    @Override
    public void addAppsList(int deviceId, List<AppEntity> appEntities) throws Exception {
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
                        appEntities.remove(app);
                    } else {
                        installApp.setAppByDeviceId(deviceEntity);
                        deviceEntity.addAppByDeviceId(installApp);
                    }
                session.save(installApp);
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
    public List<AppEntity> getAppEntityList(int deviceId) throws Exception {
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            DeviceEntity deviceEntity = (DeviceEntity) session
                    .getNamedQuery(DeviceEntity.NamedQuery.DEVICE_FIND_BY_ID)
                    .setParameter("device_id", deviceId)
                    .getSingleResult();

            return deviceEntity.getAppByDeviceId();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
