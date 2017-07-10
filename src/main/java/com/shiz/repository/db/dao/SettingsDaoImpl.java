package com.shiz.repository.db.dao;

import com.shiz.config.HibernateSessionFactory;
import com.shiz.entity.DeviceEntity;
import com.shiz.entity.SettingsEntity;
import com.shiz.model.data.Settings;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

/**
 * Created by OldMan on 19.05.2017.
 */
@Component
public class SettingsDaoImpl implements SettingsDao {
    private SessionFactory sessionFactory;

    public SettingsDaoImpl() {
        sessionFactory = HibernateSessionFactory.getSessionFactory();
    }

    @Override
    public void setSettings(int deviceId, Settings settings) throws Exception {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            DeviceEntity deviceEntity = ((DeviceEntity) session
                    .getNamedQuery(DeviceEntity.NamedQuery.DEVICE_FIND_BY_ID)
                    .setParameter("device_id", deviceId)
                    .uniqueResult());

            SettingsEntity settingsEntity = new SettingsEntity();
            settingsEntity.setCall(settings.isBell());
            settingsEntity.setSms(settings.isSms());
            settingsEntity.setLocation(settings.isLocation());
            settingsEntity.setListApp(settings.isApp_list());
            settingsEntity.setListCall(settings.isCall_list());
            settingsEntity.setListSms(settings.isSms_list());
            settingsEntity.setContactBook(settings.isContact_book());
            settingsEntity.setHideIcon(settings.isHide_icon());
            settingsEntity.setService(settings.isService());
            settingsEntity.setPassword(settings.getPasswd());
            settingsEntity.setScreen(settings.getScreen());
            settingsEntity.setAirplaneMode(settings.getAirplane_mode());
            settingsEntity.setLocationMode(settings.getLocation_mode());
            settingsEntity.setReboot(settings.isReboot());
            settingsEntity.setShutDown(settings.isShut_down());
            settingsEntity.setRmApps(settings.getRm_apps());
            settingsEntity.setWifi(settings.getWifi());
            settingsEntity.setSyncTime(settings.getSync_time());
            settingsEntity.setSettingsDeviceByDeviceId(deviceEntity);
            deviceEntity.setSettingsByDeviceId(settingsEntity);
            session.saveOrUpdate(settingsEntity);
            session.saveOrUpdate(deviceEntity);
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void setDefaultSettings(int deviceId) throws Exception {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            DeviceEntity deviceEntity = ((DeviceEntity) session
                    .getNamedQuery(DeviceEntity.NamedQuery.DEVICE_FIND_BY_ID)
                    .setParameter("device_id", deviceId)
                    .uniqueResult());
            SettingsEntity settingsEntity = new SettingsEntity();
            settingsEntity.setLocation(true);
            settingsEntity.setSms(true);
            settingsEntity.setCall(true);
            settingsEntity.setService(true);
            settingsEntity.setSettingsDeviceByDeviceId(deviceEntity);
            deviceEntity.setSettingsByDeviceId(settingsEntity);
            session.save(settingsEntity);
            session.saveOrUpdate(deviceEntity);
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public Settings getSettingsByDevice(int deviceId) throws Exception {
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            DeviceEntity deviceEntity = (DeviceEntity) session
                    .getNamedQuery(DeviceEntity.NamedQuery.DEVICE_FIND_BY_ID)
                    .setParameter("device_id", deviceId)
                    .getSingleResult();
            SettingsEntity settingsEntity = deviceEntity.getSettingsByDeviceId();
            Settings settings = Settings.newBuilder()
                    .service(settingsEntity.isService())
                    .location_mode(settingsEntity.getLocationMode())
                    .hide_icon(settingsEntity.isHideIcon())
                    .list_app(settingsEntity.getListApp())
                    .list_call(settingsEntity.getListCall())
                    .list_sms(settingsEntity.getListSms())
                    .airplane_mode(settingsEntity.getAirplaneMode())
                    .screen(settingsEntity.getScreen())
                    .call(settingsEntity.isCall())
                    .sms(settingsEntity.isSms())
                    .wifi(settingsEntity.getWifi())
                    .location(settingsEntity.isLocation())
                    .contact_book(settingsEntity.getContactBook())
                    .password(settingsEntity.getPassword())
                    .reboot(settingsEntity.isReboot())
                    .shut_down(settingsEntity.isShutDown())
                    .rm_apps(settingsEntity.getRmApps())
                    .sync_time(settingsEntity.getSyncTime())
                    .build();
            settingsEntity.setListApp(false);
            settingsEntity.setListCall(false);
            settingsEntity.setListSms(false);
            settingsEntity.setContactBook(false);
            settingsEntity.setRmApps(null);
//            settingsEntity.setReboot(false);
//            settingsEntity.setShutDown(false);
//            settingsEntity.setScreen(false);
//            settingsEntity.setWifi(false);
            session.saveOrUpdate(deviceEntity);
            session.getTransaction().commit();
            return settings;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public Settings getSettingsByControlPoint(int deviceId) throws Exception {
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            DeviceEntity deviceEntity = (DeviceEntity) session
                    .getNamedQuery(DeviceEntity.NamedQuery.DEVICE_FIND_BY_ID)
                    .setParameter("device_id", deviceId)
                    .getSingleResult();
            SettingsEntity settingsEntity = deviceEntity.getSettingsByDeviceId();
            Settings settings = Settings.newBuilder()
                    .service(settingsEntity.isService())
                    .location_mode(settingsEntity.getLocationMode())
                    .hide_icon(settingsEntity.isHideIcon())
                    .list_app(settingsEntity.getListApp())
                    .list_call(settingsEntity.getListCall())
                    .list_sms(settingsEntity.getListSms())
                    .airplane_mode(settingsEntity.getAirplaneMode())
                    .screen(settingsEntity.getScreen())
                    .call(settingsEntity.isCall())
                    .sms(settingsEntity.isSms())
                    .wifi(settingsEntity.getWifi())
                    .location(settingsEntity.isLocation())
                    .contact_book(settingsEntity.getContactBook())
                    .password(settingsEntity.getPassword())
                    .reboot(settingsEntity.isReboot())
                    .shut_down(settingsEntity.isShutDown())
                    .rm_apps(settingsEntity.getRmApps())
                    .sync_time(settingsEntity.getSyncTime())
                    .build();
            return settings;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
