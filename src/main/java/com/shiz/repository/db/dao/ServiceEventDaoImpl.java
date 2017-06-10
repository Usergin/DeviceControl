package com.shiz.repository.db.dao;

import com.shiz.config.HibernateSessionFactory;
import com.shiz.entity.DeviceEntity;
import com.shiz.entity.ServiceEventEntity;
import com.shiz.model.data.event.ServiceEvent;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by OldMan on 19.05.2017.
 */
@Component
public class ServiceEventDaoImpl implements ServiceEventDao {
    private SessionFactory sessionFactory;

    public ServiceEventDaoImpl() {
        sessionFactory = HibernateSessionFactory.getSessionFactory();
    }

    @Override
    public void addServiceEventList(int deviceId, List<ServiceEvent> serviceEventList) throws Exception {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            DeviceEntity deviceEntity = ((DeviceEntity) session
                    .getNamedQuery(DeviceEntity.NamedQuery.DEVICE_FIND_BY_ID)
                    .setParameter("device_id", deviceId)
                    .uniqueResult());
            for (ServiceEvent serviceEvent : serviceEventList) {
                ServiceEventEntity serviceEventEntity = new ServiceEventEntity();
                serviceEventEntity.setEvent(serviceEvent.getEvent());
                serviceEventEntity.setDate(new java.sql.Timestamp(serviceEvent.getDate().getTime()));
                serviceEventEntity.setArea(serviceEvent.getArea());
                serviceEventEntity.setServiceEventByDeviceId(deviceEntity);
                deviceEntity.addServiceEventByDeviceId(serviceEventEntity);
                session.save(serviceEventEntity);
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
    public List<ServiceEvent> getServiceEventList(int deviceId) throws Exception {
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            DeviceEntity deviceEntity = (DeviceEntity) session
                    .getNamedQuery(DeviceEntity.NamedQuery.DEVICE_FIND_BY_ID)
                    .setParameter("device_id", deviceId)
                    .getSingleResult();
            List<ServiceEventEntity> serviceEventEntityList = deviceEntity.getServiceEventByDeviceId();
            List<ServiceEvent> serviceEvents = new ArrayList<>();
            for (ServiceEventEntity serviceEventEntity : serviceEventEntityList) {
                ServiceEvent serviceEvent = new ServiceEvent();
                serviceEvent.setArea(serviceEventEntity.getArea());
                serviceEvent.setDate(serviceEventEntity.getDate());
                serviceEvent.setEvent(serviceEventEntity.getEvent());
                serviceEvents.add(serviceEvent);
            }
            return serviceEvents;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
