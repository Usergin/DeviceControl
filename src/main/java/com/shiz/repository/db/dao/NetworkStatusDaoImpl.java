package com.shiz.repository.db.dao;

import com.shiz.config.HibernateSessionFactory;
import com.shiz.entity.DeviceEntity;
import com.shiz.entity.NetworkStatusEntity;
import com.shiz.model.data.event.NetworkEvent;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by OldMan on 18.05.2017.
 */
@Component
public class NetworkStatusDaoImpl implements NetworkStatusDao {
    private SessionFactory sessionFactory;

    public NetworkStatusDaoImpl() {
        sessionFactory = HibernateSessionFactory.getSessionFactory();
    }

    @Override
    public void addNetworkEventList(int deviceId, List<NetworkEvent> networkEventList) throws Exception {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            DeviceEntity deviceEntity = ((DeviceEntity) session
                    .getNamedQuery(DeviceEntity.NamedQuery.DEVICE_FIND_BY_ID)
                    .setParameter("device_id", deviceId)
                    .uniqueResult());
            for (NetworkEvent networkEvent : networkEventList) {
                NetworkStatusEntity networkStatusEntity = new NetworkStatusEntity();
                networkStatusEntity.setDate(new java.sql.Timestamp(networkEvent.getDate().getTime()));
                networkStatusEntity.setIp(networkEvent.getIp());
                networkStatusEntity.setState(networkEvent.getState());
                networkStatusEntity.setNetworkStatusByDeviceId(deviceEntity);
                deviceEntity.addNetworkStatusByDeviceId(networkStatusEntity);
                session.save(networkStatusEntity);
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
    public List<NetworkEvent> getNetworkEventList(int deviceId) throws Exception {
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            DeviceEntity deviceEntity = (DeviceEntity) session
                    .getNamedQuery(DeviceEntity.NamedQuery.DEVICE_FIND_BY_ID)
                    .setParameter("device_id", deviceId)
                    .getSingleResult();
            List<NetworkStatusEntity> networkStatusEntityList = deviceEntity.getNetworkStatusByDeviceId();
            List<NetworkEvent> networkEvents = new ArrayList<>();
            for (NetworkStatusEntity networkStatusEntity : networkStatusEntityList) {
                NetworkEvent networkEvent = new NetworkEvent();
                networkEvent.setDate(networkStatusEntity.getDate());
                networkEvent.setIp(networkStatusEntity.getIp());
                networkEvent.setState(networkStatusEntity.getState());
                networkEvents.add(networkEvent);
            }
            return networkEvents;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
