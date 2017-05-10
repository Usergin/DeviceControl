package com.shiz.repository.db.dao;

import com.shiz.config.HibernateSessionFactory;
import com.shiz.entity.CallEntity;
import com.shiz.entity.DeviceEntity;
import com.shiz.model.data.event.Call;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by OldMan on 09.05.2017.
 */
@Component
public class BellDaoImpl implements BellDao {
    private SessionFactory sessionFactory;

    public BellDaoImpl() {
        sessionFactory = HibernateSessionFactory.getSessionFactory();
    }

    @Override
    public void addCallList(int deviceId, List<Call> newCallList) throws Exception {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            DeviceEntity deviceEntity = ((DeviceEntity) session
                    .getNamedQuery(DeviceEntity.NamedQuery.DEVICE_FIND_BY_ID)
                    .setParameter("device_id", deviceId)
                    .uniqueResult());
            for (Call newCall : newCallList) {
                List<CallEntity> callList = deviceEntity.getCallByDeviceId();
                for (CallEntity callEntity : callList)
                    if (newCall.getDate().equals(callEntity.getDate())) {
                        newCallList.remove(newCall);
                    } else {
                        CallEntity call = new CallEntity();
                        call.setDate(new java.sql.Timestamp(newCall.getDate().getTime()));
                        call.setTypeEventId(newCall.getType());
                        call.setNumber(newCall.getNumber());
                        call.setDuration(newCall.getDuration());
                        deviceEntity.addCallByDeviceId(call);
                        session.save(call);
                    }
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
    public List<Call> getCallEntityList(int deviceId) throws Exception {
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            DeviceEntity deviceEntity = (DeviceEntity) session
                    .getNamedQuery(DeviceEntity.NamedQuery.DEVICE_FIND_BY_ID)
                    .setParameter("device_id", deviceId)
                    .getSingleResult();
            List<CallEntity> callEntities = deviceEntity.getCallByDeviceId();
            List<Call> callList = new ArrayList<>();
            for (CallEntity callEntity : callEntities) {
                Call call = new Call();
                call.setDate(callEntity.getDate());
                call.setDuration(callEntity.getDuration());
                call.setNumber(callEntity.getNumber());
                call.setType(callEntity.getTypeEventId());
                callList.add(call);
            }
            return callList;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}