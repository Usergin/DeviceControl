package com.shiz.repository.db.dao;

import com.shiz.config.HibernateSessionFactory;
import com.shiz.entity.DeviceEntity;
import com.shiz.entity.InformationEntity;
import com.shiz.model.data.DeviceInfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

/**
 * Created by oldman on 10.05.17.
 */
@Component
public class InformationDaoImpl implements InformationDao {
    private SessionFactory sessionFactory;

    public InformationDaoImpl() {
        sessionFactory = HibernateSessionFactory.getSessionFactory();
    }


    @Override
    public void setDeviceInformation(int deviceId, DeviceInfo deviceInfo) throws Exception {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            DeviceEntity deviceEntity = ((DeviceEntity) session
                    .getNamedQuery(DeviceEntity.NamedQuery.DEVICE_FIND_BY_ID)
                    .setParameter("device_id", deviceId)
                    .uniqueResult());

            InformationEntity informationEntity = InformationEntity.newBuilder()
                    .brand(deviceInfo.getBrand())
                    .imei(deviceInfo.getImei())
                    .imsi(deviceInfo.getImsi())
                    .manufactured(deviceInfo.getManufactured())
                    .model(deviceInfo.getModel())
                    .network(deviceInfo.getNetwork())
                    .serialNum(deviceInfo.getSerialNum())
                    .versionOS(deviceInfo.getVersionOS())
                    .product(deviceInfo.getProduct())
                    .sdk(deviceInfo.getSdk())
                    .screenSize(deviceInfo.getScreenSize())
                    .imeiSim1(deviceInfo.getImeiSim1())
                    .imeiSim2(deviceInfo.getImeiSim2())
                    .isDualSim(deviceInfo.getIsDualSim())
                    .isRoot(deviceInfo.isRoot())
                    .mcc(deviceInfo.getMcc())
                    .mnc(deviceInfo.getMnc())
                    .networkType(deviceInfo.getNetworkType())
                    .operatorName(deviceInfo.getOperatorName())
                    .phoneType(deviceInfo.getPhoneType())
                    .deviceInfoByDeviceId(deviceEntity)
                    .build();

            deviceEntity.setInfoByDeviceId(informationEntity);
            session.saveOrUpdate(informationEntity);
            session.saveOrUpdate(deviceEntity);
            session.getTransaction().commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public DeviceInfo getDeviceInfo(int deviceId) throws Exception {
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            DeviceEntity deviceEntity = (DeviceEntity) session
                    .getNamedQuery(DeviceEntity.NamedQuery.DEVICE_FIND_BY_ID)
                    .setParameter("device_id", deviceId)
                    .getSingleResult();
            InformationEntity informationEntity = deviceEntity.getInfoByDeviceId();
            DeviceInfo deviceInfo = DeviceInfo.newBuilder()
                    .brand(informationEntity.getBrand())
                    .imei(informationEntity.getImei())
                    .imsi(informationEntity.getImsi())
                    .manufactured(informationEntity.getManufactured())
                    .model(informationEntity.getModel())
                    .network(informationEntity.getNetwork())
                    .serialNum(informationEntity.getSerialNum())
                    .versionOS(informationEntity.getVersionOs())
                    .product(informationEntity.getProduct())
                    .sdk(informationEntity.getSdk())
                    .screenSize(informationEntity.getScreenSize())
                    .imeiSim1(informationEntity.getImeiSim1())
                    .imeiSim2(informationEntity.getImeiSim2())
                    .isDualSim(informationEntity.getIsDualSim())
                    .isRoot(informationEntity.getIsRoot())
                    .mcc(informationEntity.getMcc())
                    .mnc(informationEntity.getMnc())
                    .networkType(informationEntity.getNetworkType())
                    .operatorName(informationEntity.getOperatorName())
                    .phoneType(informationEntity.getPhoneType())
                    .build();
            return deviceInfo;
        } finally

        {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
