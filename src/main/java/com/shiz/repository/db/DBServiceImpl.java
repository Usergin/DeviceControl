package com.shiz.repository.db;

import com.google.gson.Gson;
import com.shiz.Constants;
import com.shiz.entity.DeviceEntity;
import com.shiz.model.Authentication;
import com.shiz.model.Device;
import com.shiz.model.User;
import com.shiz.model.data.Contact;
import com.shiz.model.data.DeviceInfo;
import com.shiz.model.data.Settings;
import com.shiz.model.data.event.*;
import com.shiz.model.request.InitRequest;
import com.shiz.model.request.SyncRequest;
import com.shiz.model.request.indormation.*;
import com.shiz.model.respose.*;
import com.shiz.model.respose.error.ErrorDeviceIdResponse;
import com.shiz.model.respose.error.ErrorResponse;
import com.shiz.repository.db.dao.*;
import org.hibernate.NonUniqueResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import java.util.List;
import java.util.UUID;

/**
 * Created by oldman on 04.04.17.
 */
@Service
@Qualifier("DBService")
public class DBServiceImpl implements DBService {
    final Gson gson;
    private final DeviceDao deviceDao;
    private final ApplicationListDao applicationDao;
    private final BellDao bellDao;
    private final ContactDao contactDao;
    private final BatteryStatusDao batteryStatusDao;
    private final DeviceStatusDao deviceStatusDao;
    private final LocationDao locationDao;
    private final MessageDao messageDao;
    private final InformationDao informationDao;
    private final NetworkStatusDao networkStatusDao;
    private final ServiceEventDao serviceEventDao;
    private final SettingsDao settingsDao;
    private final UserDao userDao;

    private Logger logger = LoggerFactory.getLogger(DBServiceImpl.class);

    @Autowired
    public DBServiceImpl(ApplicationListDao applicationDao, @Qualifier("gson") Gson gson, DeviceDao deviceDao,
                         BellDao bellDao, ServiceEventDao serviceEventDao, SettingsDao settingsDao,
                         ContactDao contactDao, BatteryStatusDao batteryStatusDao, DeviceStatusDao deviceStatusDao,
                         LocationDao locationDao, MessageDao messageDao, InformationDao informationDao,
                         NetworkStatusDao networkStatusDao, UserDao userDao) {
        this.applicationDao = applicationDao;
        this.gson = gson;
        this.deviceDao = deviceDao;
        this.bellDao = bellDao;
        this.serviceEventDao = serviceEventDao;
        this.settingsDao = settingsDao;
        this.contactDao = contactDao;
        this.batteryStatusDao = batteryStatusDao;
        this.deviceStatusDao = deviceStatusDao;
        this.locationDao = locationDao;
        this.messageDao = messageDao;
        this.informationDao = informationDao;
        this.networkStatusDao = networkStatusDao;
        this.userDao = userDao;
    }

    @Override
    public ResponseEntity<BaseResponse> setNewDevice(String request) {
        InitRequest initialDeviceRequest = gson.fromJson(request, InitRequest.class);
        if (initialDeviceRequest != null) {
            try {
                int uuid = Math.abs(UUID.randomUUID().hashCode());
                DeviceEntity deviceEntity = new DeviceEntity();
                deviceEntity.setDeviceId(uuid);
                deviceEntity.setImei(initialDeviceRequest.getImei());
                deviceEntity.setModel(initialDeviceRequest.getModel());
                deviceEntity.setVersionOs(initialDeviceRequest.getVersion_os());
                int deviceId = deviceDao.addDevice(deviceEntity);
                settingsDao.setDefaultSettings(deviceId);
                System.out.print(deviceId + " " + uuid);
                if (uuid == deviceId) {
                    NewDeviceResponse newDeviceResponse = new NewDeviceResponse(Constants.STATE_OK, uuid);
                    return new ResponseEntity<>(newDeviceResponse, HttpStatus.OK);
                } else {
                    ErrorDeviceIdResponse errorResponse = new ErrorDeviceIdResponse(Constants.STATE_ERROR, Constants.DEVICE_ALREADY_REGISTERED,
                            deviceId);
                    return new ResponseEntity<>(errorResponse, HttpStatus.OK);
                }
            } catch (Exception e) {
                return getErrorResponseStatus(Constants.ERROR_ON_SERVER);
            }
        } else {
            return getErrorResponseStatus(Constants.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<BaseResponse> getDeviceByDeviceId(int deviceId) {
        try {
            logger.info("getDeviceByDeviceId: ", deviceId);
            Device device = deviceDao.getDeviceByDeviceId(deviceId);
            DeviceResponse deviceResponse = new DeviceResponse(Constants.STATE_OK, device);
            return new ResponseEntity<>(deviceResponse, HttpStatus.OK);
        } catch (NoResultException | NonUniqueResultException | NullPointerException nre) {
            logger.error("Exception NoResultException:", nre);
            return getErrorResponseStatus(Constants.NOT_FOUND_DEVICE);
        } catch (Exception e) {
            logger.error("Exception getDeviceByDeviceId:", e);
            return getErrorResponseStatus(Constants.ERROR_ON_SERVER);
        }
    }

    @Override
    public ResponseEntity<BaseResponse> getDeviceByImei(String imei) {
        try {
            logger.info("getDeviceByDeviceId: ", imei);
            Device device = deviceDao.getDeviceIdByImei(imei);
            DeviceResponse deviceResponse = new DeviceResponse(Constants.STATE_OK, device);
            return new ResponseEntity<>(deviceResponse, HttpStatus.OK);
        } catch (NoResultException | NullPointerException | NonUniqueResultException nre) {
            logger.error("Exception NoResultException:", nre);
            return getErrorResponseStatus(Constants.NOT_FOUND_DEVICE);
        } catch (Exception e) {
            logger.error("Exception getDeviceByDeviceId:", e);
            return getErrorResponseStatus(Constants.ERROR_ON_SERVER);
        }
    }

    @Override
    public ResponseEntity<BaseResponse> getDevicesList() {
        List<Device> deviceList;
        try {
            deviceList = deviceDao.getAllDevice();
            AllDevicesResponse deviceResponse = new AllDevicesResponse(Constants.STATE_OK, deviceList);
            return new ResponseEntity<>(deviceResponse, HttpStatus.OK);
        } catch (NoResultException | NullPointerException | NonUniqueResultException nre) {
            logger.error("Exception NoResultException:", nre);
            return getErrorResponseStatus(Constants.NOT_FOUND_DEVICE);
        } catch (Exception e) {
            logger.error("Exception getDeviceByDeviceId:", e);
            return getErrorResponseStatus(Constants.ERROR_ON_SERVER);
        }
    }

    @Override
    public ResponseEntity<BaseResponse> removeDeviceByDeviceId(int deviceId) {
        try {
            logger.info("removeDeviceByDeviceId: ", deviceId);
            deviceDao.deleteDeviceById(deviceId);
            BaseResponse baseResponse = new BaseResponse(Constants.STATE_OK);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        } catch (NoResultException | NullPointerException | NonUniqueResultException nre) {
            logger.error("Exception NoResultException:", nre);
            return getErrorResponseStatus(Constants.NOT_FOUND_DEVICE);
        } catch (Exception e) {
            logger.error("Exception getDeviceByDeviceId:", e);
            return getErrorResponseStatus(Constants.ERROR_ON_SERVER);
        }
    }

    @Override
    public ResponseEntity<BaseResponse> removeAllDevices() {
        try {
            logger.info("removeAllDevices");
            deviceDao.deleteAllDevice();
            BaseResponse baseResponse = new BaseResponse(Constants.STATE_OK);
            return new ResponseEntity<>(baseResponse, HttpStatus.OK);
        } catch (NoResultException | NullPointerException | NonUniqueResultException nre) {
            logger.error("Exception NoResultException:", nre);
            return getErrorResponseStatus(Constants.NOT_FOUND_DEVICE);
        } catch (Exception e) {
            logger.error("Exception getDeviceByDeviceId:", e);
            return getErrorResponseStatus(Constants.ERROR_ON_SERVER);
        }
    }

    private ResponseEntity<BaseResponse> getErrorResponseStatus(int error) {
        if (error == Constants.ERROR_ON_SERVER) {
            ErrorResponse errorResponse = new ErrorResponse(Constants.STATE_ERROR, Constants.ERROR_ON_SERVER);
            return new ResponseEntity<>(errorResponse, HttpStatus.OK);
        } else if (error == Constants.NOT_FOUND_DEVICE) {
            ErrorResponse errorResponse = new ErrorResponse(Constants.STATE_ERROR, Constants.NOT_FOUND_DEVICE);
            return new ResponseEntity<>(errorResponse, HttpStatus.OK);
        } else if (error == Constants.DEVICE_ALREADY_REGISTERED) {
            ErrorResponse errorResponse = new ErrorResponse(Constants.STATE_ERROR, Constants.DEVICE_ALREADY_REGISTERED);
            return new ResponseEntity<>(errorResponse, HttpStatus.OK);
        } else if (error == Constants.DATA_NOT_AVAILABLE) {
            ErrorResponse errorResponse = new ErrorResponse(Constants.STATE_ERROR, Constants.DATA_NOT_AVAILABLE);
            return new ResponseEntity<>(errorResponse, HttpStatus.OK);
        } else if (error == Constants.LOGIN_NOT_AVAILABLE) {
            ErrorResponse errorResponse = new ErrorResponse(Constants.STATE_ERROR, Constants.LOGIN_NOT_AVAILABLE);
            return new ResponseEntity<>(errorResponse, HttpStatus.OK);
        } else if (error == Constants.NOT_CORRECT_INPUT_AUTH_DATA) {
            ErrorResponse errorResponse = new ErrorResponse(Constants.STATE_ERROR, Constants.NOT_CORRECT_INPUT_AUTH_DATA);
            return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
        } else if (error == Constants.BAD_REQUEST) {
            ErrorResponse errorResponse = new ErrorResponse(Constants.STATE_ERROR, Constants.BAD_REQUEST);
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        } else {
            ErrorResponse errorResponse = new ErrorResponse(Constants.STATE_ERROR, Constants.ERROR_ON_SERVER);
            return new ResponseEntity<>(errorResponse, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @Override
    public ResponseEntity<BaseResponse> setInstallAppList(String request) {
        InstallAppRequest installAppRequest = gson.fromJson(request, InstallAppRequest.class);
        if (installAppRequest != null && installAppRequest.getData() != null) {
            try {
                applicationDao.addAppsList(installAppRequest.getDevice(), installAppRequest.getData());
                BaseResponse informationResponse = new BaseResponse(Constants.STATE_OK);
                return new ResponseEntity<>(informationResponse, HttpStatus.OK);

            } catch (NoResultException | NullPointerException | NonUniqueResultException nre) {
                logger.error("Exception NoResultException:", nre);
                return getErrorResponseStatus(Constants.NOT_FOUND_DEVICE);
            } catch (Exception e) {
                logger.error("Exception getDeviceByDeviceId:", e);
                return getErrorResponseStatus(Constants.ERROR_ON_SERVER);
            }
        } else {
            return getErrorResponseStatus(Constants.BAD_REQUEST);
        }

    }

    @Override
    public ResponseEntity<BaseResponse> getInstallAppList(int deviceId) {
        try {
            logger.info("getInstallAppList: ", deviceId);
            List<InstallApp> installApps = applicationDao.getAppEntityList(deviceId);
            InformationResponse deviceResponse = new InformationResponse(Constants.STATE_OK, installApps);
            return new ResponseEntity<>(deviceResponse, HttpStatus.OK);
        } catch (NoResultException | NullPointerException | NonUniqueResultException nre) {
            logger.error("Exception NoResultException:", nre);
            return getErrorResponseStatus(Constants.NOT_FOUND_DEVICE);
        } catch (Exception e) {
            logger.error("Exception getDeviceByDeviceId:", e);
            return getErrorResponseStatus(Constants.ERROR_ON_SERVER);
        }
    }

    @Override
    public ResponseEntity<BaseResponse> setBatteryStatus(String request) {
        BatteryStatusRequest informationRequest = gson.fromJson(request, BatteryStatusRequest.class);
        if (informationRequest != null && informationRequest.getData() != null) {
            try {
                batteryStatusDao.addBatteryStatus(informationRequest.getDevice(), informationRequest.getData());
                BaseResponse informationResponse = new BaseResponse(Constants.STATE_OK);
                return new ResponseEntity<>(informationResponse, HttpStatus.OK);
            } catch (NoResultException | NullPointerException | NonUniqueResultException nre) {
                System.out.print("NoResultException  " + nre);
                return getErrorResponseStatus(Constants.NOT_FOUND_DEVICE);
            } catch (Exception e) {
                System.out.print("Exception  " + e);
                return getErrorResponseStatus(Constants.BAD_REQUEST);
            }
        } else {
            return getErrorResponseStatus(Constants.BAD_REQUEST);
        }
    }


    @Override
    public ResponseEntity<BaseResponse> getBatteryStatusList(int deviceId) {
        try {
            logger.info("getBatteryStatusList: ", deviceId);
            List<BatteryEvent> batteryEntities = batteryStatusDao.getBatteryEventList(deviceId);
            InformationResponse deviceResponse = new InformationResponse(Constants.STATE_OK, batteryEntities);
            return new ResponseEntity<>(deviceResponse, HttpStatus.OK);
        } catch (NoResultException | NullPointerException | NonUniqueResultException nre) {
            logger.error("Exception NoResultException:", nre);
            return getErrorResponseStatus(Constants.NOT_FOUND_DEVICE);
        } catch (Exception e) {
            logger.error("Exception getDeviceByDeviceId:", e);
            return getErrorResponseStatus(Constants.ERROR_ON_SERVER);
        }
    }

    @Override
    public ResponseEntity<BaseResponse> setCallList(String request) {
        CallRequest callRequest = gson.fromJson(request, CallRequest.class);
        if (callRequest != null && callRequest.getData() != null) {
            try {
                bellDao.addCallList(callRequest.getDevice(), callRequest.getData());
                BaseResponse informationResponse = new BaseResponse(Constants.STATE_OK);
                return new ResponseEntity<>(informationResponse, HttpStatus.OK);

            } catch (NoResultException | NullPointerException | NonUniqueResultException nre) {
                logger.error("Exception NoResultException:", nre);
                return getErrorResponseStatus(Constants.NOT_FOUND_DEVICE);
            } catch (Exception e) {
                logger.error("Exception getDeviceByDeviceId:", e);
                return getErrorResponseStatus(Constants.ERROR_ON_SERVER);
            }
        } else {
            return getErrorResponseStatus(Constants.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<BaseResponse> getCallList(int deviceId) {
        try {
            logger.info("getCallList: ", deviceId);
            List<Call> callList = bellDao.getCallList(deviceId);
            InformationResponse deviceResponse = new InformationResponse(Constants.STATE_OK, callList);
            return new ResponseEntity<>(deviceResponse, HttpStatus.OK);
        } catch (NoResultException | NullPointerException | NonUniqueResultException nre) {
            logger.error("Exception NoResultException:", nre);
            return getErrorResponseStatus(Constants.NOT_FOUND_DEVICE);
        } catch (Exception e) {
            logger.error("Exception getDeviceByDeviceId:", e);
            return getErrorResponseStatus(Constants.ERROR_ON_SERVER);
        }
    }

    @Override
    public ResponseEntity<BaseResponse> getContactList(int deviceId) {
        try {
            logger.info("getNetworkStatusList: ", deviceId);
            List<Contact> contactList = contactDao.getContactList(deviceId);
            InformationResponse deviceResponse = new InformationResponse(Constants.STATE_OK, contactList);
            return new ResponseEntity<>(deviceResponse, HttpStatus.OK);
        } catch (NoResultException |NullPointerException |  NonUniqueResultException nre) {
            logger.error("Exception NoResultException:", nre);
            return getErrorResponseStatus(Constants.NOT_FOUND_DEVICE);
        } catch (Exception e) {
            logger.error("Exception getDeviceByDeviceId:", e);
            return getErrorResponseStatus(Constants.ERROR_ON_SERVER);
        }
    }

    @Override
    public ResponseEntity<BaseResponse> setContactList(String request) {
        ContactRequest contactRequest = gson.fromJson(request, ContactRequest.class);
        if (contactRequest != null && contactRequest.getData() != null) {
            try {
                contactDao.addContactList(contactRequest.getDevice(), contactRequest.getData());
                BaseResponse informationResponse = new BaseResponse(Constants.STATE_OK);
                return new ResponseEntity<>(informationResponse, HttpStatus.OK);
            } catch (NoResultException | NullPointerException | NonUniqueResultException nre) {
                System.out.print("NoResultException  " + nre);
                return getErrorResponseStatus(Constants.NOT_FOUND_DEVICE);
            } catch (Exception e) {
                System.out.print("Exception  " + e);
                return getErrorResponseStatus(Constants.BAD_REQUEST);
            }
        } else {
            ErrorResponse errorResponse = new ErrorResponse(Constants.STATE_ERROR, Constants.BAD_REQUEST);
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<BaseResponse> setDeviceStatus(String request) {
        DeviceStatusRequest informationRequest = gson.fromJson(request, DeviceStatusRequest.class);
        if (informationRequest != null && informationRequest.getData() != null) {
            try {
                deviceStatusDao.addDeviceStatus(informationRequest.getDevice(), informationRequest.getData());
                BaseResponse informationResponse = new BaseResponse(Constants.STATE_OK);
                return new ResponseEntity<>(informationResponse, HttpStatus.OK);
            } catch (NoResultException | NullPointerException | NonUniqueResultException nre) {
                System.out.print("NoResultException  " + nre);
                return getErrorResponseStatus(Constants.NOT_FOUND_DEVICE);
            } catch (Exception e) {
                System.out.print("Exception  " + e);
                return getErrorResponseStatus(Constants.BAD_REQUEST);
            }
        } else {
            ErrorResponse errorResponse = new ErrorResponse(Constants.STATE_ERROR, Constants.BAD_REQUEST);
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<BaseResponse> getDeviceStatusList(int deviceId) {
        try {
            logger.info("getInstallAppList: ", deviceId);
            List<DeviceEvent> deviceStatusList = deviceStatusDao.getDeviceStatusList(deviceId);
            InformationResponse deviceResponse = new InformationResponse(Constants.STATE_OK, deviceStatusList);
            return new ResponseEntity<>(deviceResponse, HttpStatus.OK);
        } catch (NoResultException | NullPointerException | NonUniqueResultException nre) {
            logger.error("Exception NoResultException:", nre);
            return getErrorResponseStatus(Constants.NOT_FOUND_DEVICE);
        } catch (Exception e) {
            logger.error("Exception getDeviceByDeviceId:", e);
            return getErrorResponseStatus(Constants.ERROR_ON_SERVER);
        }
    }

    @Override
    public ResponseEntity<BaseResponse> setDeviceInfo(String request) {
        DeviceInformationRequest deviceInfoRequest = gson.fromJson(request, DeviceInformationRequest.class);
        if (deviceInfoRequest != null) {
            try {
                logger.info("setDeviceInfo" + deviceInfoRequest.getDevice());
                informationDao.setDeviceInformation(deviceInfoRequest.getDevice(), deviceInfoRequest.getData());
                BaseResponse informationResponse = new BaseResponse(Constants.STATE_OK);
                return new ResponseEntity<>(informationResponse, HttpStatus.OK);
            } catch (NoResultException |NullPointerException |  NonUniqueResultException nre) {
                System.out.print("NoResultException  " + nre);
                return getErrorResponseStatus(Constants.NOT_FOUND_DEVICE);
            } catch (Exception e) {
                System.out.print("Exception  " + e);
                return getErrorResponseStatus(Constants.BAD_REQUEST);
            }
        } else {
            ErrorResponse errorResponse = new ErrorResponse(Constants.STATE_ERROR, Constants.BAD_REQUEST);
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<BaseResponse> getDeviceInfo(int deviceId) {
        try {
            logger.info("getData: ", deviceId);
            DeviceInfo deviceInfo = informationDao.getDeviceInfo(deviceId);
            InformationResponse deviceResponse = new InformationResponse(Constants.STATE_OK, deviceInfo);
            return new ResponseEntity<>(deviceResponse, HttpStatus.OK);
        } catch (NoResultException | NullPointerException | NonUniqueResultException nre) {
            logger.error("Exception NoResultException:", nre);
            return getErrorResponseStatus(Constants.NOT_FOUND_DEVICE);
        } catch (Exception e) {
            logger.error("Exception getDeviceByDeviceId:", e);
            return getErrorResponseStatus(Constants.ERROR_ON_SERVER);
        }
    }

    @Override
    public ResponseEntity<BaseResponse> setLocationList(String request) {
        LocationRequest locationRequest = gson.fromJson(request, LocationRequest.class);
        if (locationRequest != null && locationRequest.getData() != null) {
            try {
                locationDao.addLocationList(locationRequest.getDevice(), locationRequest.getData());
                BaseResponse informationResponse = new BaseResponse(Constants.STATE_OK);
                return new ResponseEntity<>(informationResponse, HttpStatus.OK);
            } catch (NoResultException | NullPointerException | NonUniqueResultException nre) {
                System.out.print("NoResultException  " + nre);
                return getErrorResponseStatus(Constants.NOT_FOUND_DEVICE);
            } catch (Exception e) {
                System.out.print("Exception  " + e);
                return getErrorResponseStatus(Constants.BAD_REQUEST);
            }
        } else {
            return getErrorResponseStatus(Constants.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<BaseResponse> getLocationList(int deviceId) {
        try {
            logger.info("getLocationList: ", deviceId);
            List<Location> locationEntityList = locationDao.getLocationList(deviceId);
            InformationResponse deviceResponse = new InformationResponse(Constants.STATE_OK, locationEntityList);
            return new ResponseEntity<>(deviceResponse, HttpStatus.OK);
        } catch (NoResultException | NullPointerException | NonUniqueResultException nre) {
            logger.error("Exception NoResultException:", nre);
            return getErrorResponseStatus(Constants.NOT_FOUND_DEVICE);
        } catch (Exception e) {
            logger.error("Exception getDeviceByDeviceId:", e);
            return getErrorResponseStatus(Constants.ERROR_ON_SERVER);
        }
    }

    @Override
    public ResponseEntity<BaseResponse> setMessageList(String request) {
        SmsRequest smsRequest = gson.fromJson(request, SmsRequest.class);
        if (smsRequest != null && smsRequest.getData() != null) {
            try {
                messageDao.addMessageList(smsRequest.getDevice(), smsRequest.getData());
                BaseResponse informationResponse = new BaseResponse(Constants.STATE_OK);
                return new ResponseEntity<>(informationResponse, HttpStatus.OK);
            } catch (NoResultException | NullPointerException | NonUniqueResultException nre) {
                System.out.print("NoResultException  " + nre);
                return getErrorResponseStatus(Constants.NOT_FOUND_DEVICE);
            } catch (Exception e) {
                System.out.print("Exception  " + e);
                return getErrorResponseStatus(Constants.BAD_REQUEST);
            }
        } else {
            return getErrorResponseStatus(Constants.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<BaseResponse> getMessageList(int deviceId) {
        try {
            logger.info("getMessageList: ", deviceId);
            List<Message> messageEntityList = messageDao.getMessageEntityList(deviceId);
            InformationResponse deviceResponse = new InformationResponse(Constants.STATE_OK, messageEntityList);
            return new ResponseEntity<>(deviceResponse, HttpStatus.OK);
        } catch (NoResultException | NullPointerException | NonUniqueResultException nre) {
            logger.error("Exception NoResultException:", nre);
            return getErrorResponseStatus(Constants.NOT_FOUND_DEVICE);
        } catch (Exception e) {
            logger.error("Exception getDeviceByDeviceId:", e);
            return getErrorResponseStatus(Constants.ERROR_ON_SERVER);
        }
    }

    @Override
    public ResponseEntity<BaseResponse> setNetworkStatusList(String request) {
        NetworkStatusRequest informationRequest = gson.fromJson(request, NetworkStatusRequest.class);
        if (informationRequest != null && informationRequest.getData() != null) {
            try {
                networkStatusDao.addNetworkEventList(informationRequest.getDevice(), informationRequest.getData());
                BaseResponse informationResponse = new BaseResponse(Constants.STATE_OK);
                return new ResponseEntity<>(informationResponse, HttpStatus.OK);
            } catch (NoResultException |NullPointerException| NonUniqueResultException nre) {
                System.out.print("NoResultException  " + nre);
                return getErrorResponseStatus(Constants.NOT_FOUND_DEVICE);
            } catch (Exception e) {
                System.out.print("Exception  " + e);
                return getErrorResponseStatus(Constants.BAD_REQUEST);
            }
        } else {
            ErrorResponse errorResponse = new ErrorResponse(Constants.STATE_ERROR, Constants.BAD_REQUEST);
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<BaseResponse> getNetworkStatusList(int deviceId) {
        try {
            logger.info("getNetworkStatusList: ", deviceId);
            List<NetworkEvent> networkEventList = networkStatusDao.getNetworkEventList(deviceId);
            InformationResponse deviceResponse = new InformationResponse(Constants.STATE_OK, networkEventList);
            return new ResponseEntity<>(deviceResponse, HttpStatus.OK);
        } catch (NoResultException | NullPointerException|NonUniqueResultException nre) {
            logger.error("Exception NoResultException:", nre);
            return getErrorResponseStatus(Constants.NOT_FOUND_DEVICE);
        } catch (Exception e) {
            logger.error("Exception getDeviceByDeviceId:", e);
            return getErrorResponseStatus(Constants.ERROR_ON_SERVER);
        }
    }

    @Override
    public ResponseEntity<BaseResponse> setServiceEventList(String request) {
        ServiceEventRequest serviceEventRequest = gson.fromJson(request, ServiceEventRequest.class);
        if (serviceEventRequest != null && serviceEventRequest.getData() != null) {
            try {
                serviceEventDao.addServiceEventList(serviceEventRequest.getDevice(), serviceEventRequest.getData());
                BaseResponse informationResponse = new BaseResponse(Constants.STATE_OK);
                return new ResponseEntity<>(informationResponse, HttpStatus.OK);
            } catch (NoResultException |NullPointerException|  NonUniqueResultException nre) {
                System.out.print("NoResultException  " + nre);
                return getErrorResponseStatus(Constants.NOT_FOUND_DEVICE);
            } catch (Exception e) {
                System.out.print("Exception  " + e);
                return getErrorResponseStatus(Constants.BAD_REQUEST);
            }
        } else {
            return getErrorResponseStatus(Constants.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<BaseResponse> getServiceEventList(int deviceId) {
        try {
            logger.info("getServiceEventList: ", deviceId);
            List<ServiceEvent> serviceEventList = serviceEventDao.getServiceEventList(deviceId);
            InformationResponse deviceResponse = new InformationResponse(Constants.STATE_OK, serviceEventList);
            return new ResponseEntity<>(deviceResponse, HttpStatus.OK);
        } catch (NoResultException | NullPointerException| NonUniqueResultException nre) {
            logger.info("Exception NoResultException:", nre);
            return getErrorResponseStatus(Constants.NOT_FOUND_DEVICE);
        } catch (Exception e) {
            logger.info("Exception getDeviceByDeviceId:", e);
            return getErrorResponseStatus(Constants.ERROR_ON_SERVER);
        }
    }

    @Override
    public ResponseEntity<BaseResponse> setSettings(String request) {
        SettingsRequest settingsRequest = gson.fromJson(request, SettingsRequest.class);
        logger.info("setSettings " + request);
        if (settingsRequest != null && settingsRequest.getData() != null) {
            try {
                settingsDao.setSettings(settingsRequest.getDevice(), settingsRequest.getData());
                BaseResponse informationResponse = new BaseResponse(Constants.STATE_OK);
                return new ResponseEntity<>(informationResponse, HttpStatus.OK);
            } catch (NoResultException | NullPointerException | NonUniqueResultException nre) {
                logger.info("setSettings NoResultException  " + nre);
                return getErrorResponseStatus(Constants.NOT_FOUND_DEVICE);
            } catch (Exception e) {
                logger.info("setSettings Exception  " + e);
                return getErrorResponseStatus(Constants.BAD_REQUEST);
            }
        } else {
            ErrorResponse errorResponse = new ErrorResponse(Constants.STATE_ERROR, Constants.BAD_REQUEST);
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }


    @Override
    public ResponseEntity<BaseResponse> getSettingsByDevice(String request) {
        SyncRequest syncRequest = gson.fromJson(request, SyncRequest.class);
        try {
            if (syncRequest != null) {
                Settings settings = settingsDao.getSettingsByDevice(syncRequest.getDevice());
                PeriodicalResponse periodicalResponse = new PeriodicalResponse(Constants.STATE_OK, settings);
                return new ResponseEntity<>(periodicalResponse, HttpStatus.OK);
            } else {
                logger.info("Exception getSettingsByDevice: not found");
                return getErrorResponseStatus(Constants.NOT_FOUND_DEVICES);
            }
        } catch (NullPointerException e) {
            logger.info("getSettingsByDevice history already exist");
            return getErrorResponseStatus(Constants.NOT_FOUND_DEVICES);
        } catch (Exception e) {
            logger.info("Exception getSettingsDevice: " + e);
            return getErrorResponseStatus(Constants.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<BaseResponse> getSettingsByControlPoint(int deviceId) {
        try {
            Settings settings = settingsDao.getSettingsByControlPoint(deviceId);
            PeriodicalResponse periodicalResponse = new PeriodicalResponse(Constants.STATE_OK, settings);
            return new ResponseEntity<>(periodicalResponse, HttpStatus.OK);
        } catch (NullPointerException e) {
            logger.info("history already exist");
            return getErrorResponseStatus(Constants.NOT_FOUND_DEVICES);
        } catch (Exception e) {
            logger.info("Exception getSettingsDevice: " + e);
            return getErrorResponseStatus(Constants.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<BaseResponse> addUser(String request) {
        User user = gson.fromJson(request, User.class);
        if (user != null) {
            try {
                userDao.addUser(user);
                BaseResponse informationResponse = new BaseResponse(Constants.STATE_OK);
                return new ResponseEntity<>(informationResponse, HttpStatus.OK);
            } catch (NonUniqueResultException nre) {
                logger.info("addUser NoResultException  " + nre);
                return getErrorResponseStatus(Constants.LOGIN_NOT_AVAILABLE);
            } catch (PersistenceException e) {
                logger.info("history already exist");
                return getErrorResponseStatus(Constants.LOGIN_NOT_AVAILABLE);
            } catch (Exception e) {
                logger.info("Exception  " + e);
                return getErrorResponseStatus(Constants.BAD_REQUEST);
            }
        } else {
            ErrorResponse errorResponse = new ErrorResponse(Constants.STATE_ERROR, Constants.BAD_REQUEST);
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<BaseResponse> getUser(String request) {
        logger.info("getUser: " + request);
        Authentication authentication = gson.fromJson(request, Authentication.class);
        try {
            if (authentication != null) {
                User user = userDao.getUser(authentication);
                InformationResponse periodicalResponse = new InformationResponse(Constants.STATE_OK, user);
                return new ResponseEntity<>(periodicalResponse, HttpStatus.OK);
            } else {
                logger.error("Exception NOT_CORRECT_INPUT_AUTH_DATA");
                return getErrorResponseStatus(Constants.BAD_REQUEST);
            }
        } catch (NullPointerException e) {
            logger.error("NullPointerException" + e);
            return getErrorResponseStatus(Constants.NOT_CORRECT_INPUT_AUTH_DATA);
        } catch (Exception e) {
            logger.error("Exception getUser: " + e);
            return getErrorResponseStatus(Constants.BAD_REQUEST);
        }
    }
}
