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
            settingsEntity.setListApp(settings.isListApp());
            settingsEntity.setListCall(settings.isListCall());
            settingsEntity.setListSms(settings.isListSms());
            settingsEntity.setContactBook(settings.isContactBook());
            settingsEntity.setHideIcon(settings.isHideIcon());
            settingsEntity.setService(settings.isService());
            settingsEntity.setPassword(settings.getPasswd());
            settingsEntity.setScreen(settings.isScreen());
            settingsEntity.setAirplaneMode(settings.isAirplaneMode());
            settingsEntity.setLocationMode(settings.isLocationMode());
            settingsEntity.setReboot(settings.isReboot());
            settingsEntity.setShutDown(settings.isShutDown());
            settingsEntity.setRmApps(settings.getRmApps());
            settingsEntity.setWifi(settings.isWifi());
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
                    .airplane_mode(settingsEntity.isAirplaneMode())
                    .screen(settingsEntity.isScreen())
                    .call(settingsEntity.isCall())
                    .sms(settingsEntity.isSms())
                    .wifi(settingsEntity.isWifi())
                    .location(settingsEntity.isLocation())
                    .contact_book(settingsEntity.getContactBook())
                    .password(settingsEntity.getPassword())
                    .reboot(settingsEntity.isReboot())
                    .shut_down(settingsEntity.isShutDown())
                    .rm_apps(settingsEntity.getRmApps())
                    .build();
            settingsEntity.setAirplaneMode(false);
            settingsEntity.setListApp(false);
            settingsEntity.setListCall(false);
            settingsEntity.setListSms(false);
            settingsEntity.setContactBook(false);
            settingsEntity.setReboot(false);
            settingsEntity.setShutDown(false);
            settingsEntity.setRmApps(null);
            settingsEntity.setScreen(false);
            settingsEntity.setWifi(false);
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
                    .airplane_mode(settingsEntity.isAirplaneMode())
                    .screen(settingsEntity.isScreen())
                    .call(settingsEntity.isCall())
                    .sms(settingsEntity.isSms())
                    .wifi(settingsEntity.isWifi())
                    .location(settingsEntity.isLocation())
                    .contact_book(settingsEntity.getContactBook())
                    .password(settingsEntity.getPassword())
                    .reboot(settingsEntity.isReboot())
                    .shut_down(settingsEntity.isShutDown())
                    .rm_apps(settingsEntity.getRmApps())
                    .build();
            return settings;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
