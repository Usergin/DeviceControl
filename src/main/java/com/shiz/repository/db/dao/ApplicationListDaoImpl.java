package com.shiz.repository.db.dao;

import com.shiz.config.HibernateSessionFactory;
import com.shiz.entity.AppEntity;
import com.shiz.entity.DeviceEntity;
import com.shiz.model.data.event.InstallApp;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oldman on 22.04.17.
 */
@Component
public class ApplicationListDaoImpl implements ApplicationListDao {
    private SessionFactory sessionFactory;

    public ApplicationListDaoImpl() {
        sessionFactory = HibernateSessionFactory.getSessionFactory();
    }

    @Override
    public void addAppsList(int deviceId, List<InstallApp> installApps) throws Exception {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            DeviceEntity deviceEntity = ((DeviceEntity) session
                    .getNamedQuery(DeviceEntity.NamedQuery.DEVICE_FIND_BY_ID)
                    .setParameter("device_id", deviceId)
                    .uniqueResult());
            for (InstallApp installApp : installApps) {
                Criteria userCriteria = session.createCriteria(AppEntity.class);
                userCriteria.add(Restrictions.like("name", installApp.getName()));
                AppEntity app = (AppEntity) userCriteria.uniqueResult();
                if (app == null) {
                    AppEntity appEntity = new AppEntity();
                    appEntity.setDateInstalled(new java.sql.Timestamp(installApp.getDate().getTime()));
                    appEntity.setInfo(installApp.getInfo());
                    appEntity.setName(installApp.getName());
                    appEntity.setAppByDeviceId(deviceEntity);
                    deviceEntity.addAppByDeviceId(appEntity);
                    session.save(appEntity);
                } else {
                    app.setDateInstalled(new java.sql.Timestamp(installApp.getDate().getTime()));
                    app.setInfo(installApp.getInfo());
                    app.setName(installApp.getName());
                    session.saveOrUpdate(app);
                }
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
    public List<InstallApp> getAppEntityList(int deviceId) throws Exception {
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            DeviceEntity deviceEntity = (DeviceEntity) session
                    .getNamedQuery(DeviceEntity.NamedQuery.DEVICE_FIND_BY_ID)
                    .setParameter("device_id", deviceId)
                    .getSingleResult();
            List<AppEntity> appEntities = deviceEntity.getAppByDeviceId();
            List<InstallApp> installApps = new ArrayList<>();
            for (AppEntity appEntity : appEntities) {
                InstallApp installApp = new InstallApp();
                installApp.setDate(appEntity.getDateInstalled());
                installApp.setInfo(appEntity.getInfo());
                installApp.setName(appEntity.getName());
                installApps.add(installApp);
            }
            return installApps;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
