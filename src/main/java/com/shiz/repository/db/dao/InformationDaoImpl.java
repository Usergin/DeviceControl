package com.shiz.repository.db.dao;

import com.shiz.config.HibernateSessionFactory;
import com.shiz.entity.DeviceEntity;
import com.shiz.entity.InformationEntity;
import com.shiz.model.data.DeviceInfo;
import com.shiz.repository.db.DBServiceImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
            InformationEntity informationEntity = new InformationEntity();
            informationEntity.setBrand(deviceInfo.getBrand());
            informationEntity.setImei(deviceInfo.getImei());
            informationEntity.setImsi(deviceInfo.getImsi());
            informationEntity.setManufactured(deviceInfo.getManufactured());
            informationEntity.setModel(deviceInfo.getModel());
            informationEntity.setNetwork(deviceInfo.getNetwork());
            informationEntity.setSerialNum(deviceInfo.getSerial_num());
            informationEntity.setVersionOs(deviceInfo.getVersion_os());
            informationEntity.setProduct(deviceInfo.getProduct());
            informationEntity.setSdk(deviceInfo.getSdk());
            informationEntity.setScreenSize(deviceInfo.getScreen_size());
            informationEntity.setImeiSim1(deviceInfo.getImei_sim1());
            informationEntity.setImeiSim2(deviceInfo.getImei_sim2());
            informationEntity.setIsDualSim(deviceInfo.getIs_dual_sim());
            informationEntity.setIsRoot(deviceInfo.isRoot());
            informationEntity.setMcc(deviceInfo.getMcc());
            informationEntity.setMnc(deviceInfo.getMnc());
            informationEntity.setNetworkType(deviceInfo.getNetwork_type());
            informationEntity.setOperatorName(deviceInfo.getOperator_name());
            informationEntity.setPhoneType(deviceInfo.getPhone_type());
            informationEntity.setDeviceInfoByDeviceId(deviceEntity);
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
            return DeviceInfo.newBuilder()
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
        } finally{
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
