package com.shiz.repository.db;

import com.google.gson.Gson;
import com.shiz.Constants;
import com.shiz.entity.*;
import com.shiz.model.Device;
import com.shiz.model.data.Contact;
import com.shiz.model.data.DeviceInfo;
import com.shiz.model.data.Settings;
import com.shiz.model.data.event.*;
import com.shiz.model.request.InitialDeviceRequest;
import com.shiz.model.request.SyncRequest;
import com.shiz.model.request.indormation.*;
import com.shiz.model.respose.*;
import com.shiz.model.respose.error.ErrorDeviceIdResponse;
import com.shiz.model.respose.error.ErrorResponse;
import com.shiz.repository.db.dao.*;
import com.shiz.repository.exception.DeviceException;
import org.hibernate.NonUniqueResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by oldman on 04.04.17.
 */
@Service
@Qualifier("DBService")
public class DBServiceImpl implements DBService {
    @Autowired
    @Qualifier("gson")
    Gson gson;
    @Autowired
    private DeviceDao deviceDao;
    @Autowired
    private ApplicationDao applicationDao;
    @Autowired
    private BellDao bellDao;
    @Autowired
    private BatteryStatusDao batteryStatusDao;
    @Autowired
    private DeviceStatusDao deviceStatusDao;
    @Autowired
    private LocationDao locationDao;
    @Autowired
    private MessageDao messageDao;
    @Autowired
    private InformationDao informationDao;

    private Logger logger = LoggerFactory.getLogger(DBServiceImpl.class);

    //    @Autowired
//    @Qualifier("hibernateSessionFactory")
//    private SessionFactory sessionFactory;

    public ResponseEntity<BaseResponse> getDeviceByDeviceId(int deviceId) {
        try {
            logger.info("getDeviceByDeviceId: ", deviceId);
            DeviceEntity deviceEntity = deviceDao.getDeviceByDeviceId(deviceId);
            Device device = Device.newBuilder()
                    .imei(deviceEntity.getImei())
                    .id(deviceEntity.getDeviceId())
                    .build();
            DeviceResponse deviceResponse = new DeviceResponse(Constants.STATE_OK, device);
            return new ResponseEntity<>(deviceResponse, HttpStatus.OK);
        } catch (NoResultException | NonUniqueResultException nre) {
            logger.error("Exception NoResultException:", nre);
            return getErrorResponseStatus(Constants.NOT_FOUND_DEVICE);
        } catch (Exception e) {
            logger.error("Exception getDeviceByDeviceId:", e);
            return getErrorResponseStatus(Constants.ERROR_ON_SERVER);
        }
    }

    public ResponseEntity<BaseResponse> getDevicesList() {
        List<DeviceEntity> deviceEntityList;
        List<Device> deviceList = new ArrayList<>();
        try {
            deviceEntityList = deviceDao.getAllDevice();
            if (deviceEntityList != null && deviceEntityList.size() != 0) {
                for (DeviceEntity deviceEntity : deviceEntityList) {
                    Device device = Device.newBuilder()
                            .imei(deviceEntity.getImei())
                            .id(deviceEntity.getDeviceId())
                            .build();
                    deviceList.add(device);
                }
                AllDevicesResponse deviceResponse = new AllDevicesResponse(Constants.STATE_OK, deviceList);
                return new ResponseEntity<>(deviceResponse, HttpStatus.OK);
            } else {
                logger.error("Exception getDevicesList: not found");
                return getErrorResponseStatus(Constants.NOT_FOUND_DEVICES);
            }
        } catch (Exception e) {
            logger.error("Exception getDevicesList: " + e);
            return getErrorResponseStatus(Constants.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<BaseResponse> removeDeviceByDeviceId(int deviceId) {
        return null;
    }

    @Override
    public ResponseEntity<BaseResponse> removeAllDevices() {
        return null;
    }

    public Device getLocationDevice(int deviceId) {
        // имитируем обращение к БД
        if (deviceId == 2) {
            return null;
        } else {
            throw new DeviceException(deviceId);
        }
    }

    public ResponseEntity<BaseResponse> setNewDevice(String request) {
        InitialDeviceRequest initialDeviceRequest = gson.fromJson(request, InitialDeviceRequest.class);
        if (initialDeviceRequest != null) {
            try {
                int uuid = Math.abs(UUID.randomUUID().hashCode());
                DeviceEntity deviceEntity = new DeviceEntity();
                deviceEntity.setImei(initialDeviceRequest.getImei());
                deviceEntity.setDeviceId(uuid);
                int deviceId = deviceDao.addDevice(deviceEntity);
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


    public ResponseEntity<BaseResponse> setDeviceTelephoneBook(String request) {
        // имитируем обращение к БД
        TelephoneBookRequest telephoneBookRequest = gson.fromJson(request, TelephoneBookRequest.class);
        if (telephoneBookRequest != null) {
            for (Contact call : telephoneBookRequest.getData()) {
                System.out.print("type  " + call.getName());
            }
            BaseResponse informationResponse = new BaseResponse(Constants.STATE_OK);
            return new ResponseEntity<>(informationResponse, HttpStatus.OK);
        } else {
            return getErrorResponseStatus(Constants.BAD_REQUEST);
        }
    }


    private ResponseEntity<BaseResponse> getErrorResponseStatus(int error) {
        if (error == Constants.ERROR_ON_SERVER) {
            ErrorResponse errorResponse = new ErrorResponse(Constants.STATE_ERROR, Constants.ERROR_ON_SERVER);
            return new ResponseEntity<>(errorResponse, HttpStatus.OK);
        } else if (error == Constants.BAD_REQUEST) {
            ErrorResponse errorResponse = new ErrorResponse(Constants.STATE_ERROR, Constants.BAD_REQUEST);
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        } else {
            ErrorResponse errorResponse = new ErrorResponse(Constants.STATE_ERROR, Constants.ERROR_ON_SERVER);
            return new ResponseEntity<>(errorResponse, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
//
//    private ResponseEntity<BaseResponse> getResponseStatus(int device) {
//        if (device == -1) {
//            ErrorResponse errorResponse = new ErrorResponse(Constants.STATE_ERROR, Constants.NOT_FOUND_DEVICE);
//            return new ResponseEntity<>(errorResponse, HttpStatus.OK);
//        } else if (device == 0) {
//            ErrorResponse errorResponse = new ErrorResponse(Constants.STATE_ERROR, Constants.DATA_NOT_AVAILABLE);
//            return new ResponseEntity<>(errorResponse, HttpStatus.OK);
//        } else {
//            BaseResponse informationResponse = new BaseResponse(Constants.STATE_OK);
//            return new ResponseEntity<>(informationResponse, HttpStatus.OK);
//        }
//    }


    public ResponseEntity<BaseResponse> setDeviceNetworkStatus(String request) {
        // имитируем обращение к БД
        NetworkStatusRequest informationRequest = gson.fromJson(request, NetworkStatusRequest.class);
        if (informationRequest != null) {
            NetworkEvent deviceEvent = informationRequest.getData();
            System.out.print("type  " + deviceEvent.getState());

            BaseResponse informationResponse = new BaseResponse(Constants.STATE_OK);
            return new ResponseEntity<>(informationResponse, HttpStatus.OK);
        } else {
            ErrorResponse errorResponse = new ErrorResponse(Constants.STATE_ERROR, Constants.BAD_REQUEST);
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }

//


    //    public ResponseEntity<Device> getDeviceNetworkStatus(int deviceId) {
//        // имитируем обращение к БД
//        if (deviceId == 2) {
//            Contact contact = new Contact("12321", "123121", "sadasdsa");
//            List<Contact> contactList = new ArrayList<>();
//            contactList.add(contact);
//            Device device = Device.newBuilder().contact_list(contactList).build();
//            return new ResponseEntity<Device>(device, HttpStatus.OK);
//        } else {
//            throw new DeviceException(deviceId);
//        }
//    }
//
    public ResponseEntity<BaseResponse> getDeviceStatus(int deviceId) {
        try {
            logger.info("getInstallAppList: ", deviceId);
            List<DeviceStatusEntity> deviceStatusEntityList = deviceStatusDao.getDeviceStatusEntityList(deviceId);
            List<DeviceEvent> deviceEvents = new ArrayList<>();
            for (DeviceStatusEntity deviceStatusEntity : deviceStatusEntityList) {
                DeviceEvent deviceEvent = new DeviceEvent();
                deviceEvent.setDate(deviceStatusEntity.getDate());
                deviceEvent.setStatus(deviceStatusEntity.getStatus());
                deviceEvents.add(deviceEvent);
            }
            InformationResponse deviceResponse = new InformationResponse(Constants.STATE_OK, deviceEvents);
            return new ResponseEntity<>(deviceResponse, HttpStatus.OK);
        } catch (NoResultException | NonUniqueResultException nre) {
            logger.error("Exception NoResultException:", nre);
            return getErrorResponseStatus(Constants.NOT_FOUND_DEVICE);
        } catch (Exception e) {
            logger.error("Exception getDeviceByDeviceId:", e);
            return getErrorResponseStatus(Constants.ERROR_ON_SERVER);
        }
    }

    public ResponseEntity<BaseResponse> setDeviceStatus(String request) {
        // имитируем обращение к БД
        DeviceStatusRequest informationRequest = gson.fromJson(request, DeviceStatusRequest.class);
        if (informationRequest != null && informationRequest.getData() != null) {
            DeviceEvent deviceEvent = informationRequest.getData();
            DeviceStatusEntity deviceStatusEntity = new DeviceStatusEntity();
            deviceStatusEntity.setDate(new java.sql.Timestamp(deviceEvent.getDate().getTime()));
            deviceStatusEntity.setStatus(deviceEvent.getStatus());
            try {
                deviceStatusDao.addDeviceStatus(informationRequest.getDevice(), deviceStatusEntity);
                BaseResponse informationResponse = new BaseResponse(Constants.STATE_OK);
                return new ResponseEntity<>(informationResponse, HttpStatus.OK);
            } catch (NoResultException | NonUniqueResultException nre) {
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

    public ResponseEntity<BaseResponse> getSettingsDevice(String request) {
        // имитируем обращение к БД
        SyncRequest syncRequest = gson.fromJson(request, SyncRequest.class);
        try {
            if (syncRequest != null) {
                SettingsEntity settingsEntity = deviceDao.getSettingsEntity(syncRequest.getDevice());
                Settings settings = Settings.newBuilder()
                        .is_service(settingsEntity.getIsService())
                        .location_mode(settingsEntity.getLocationMode())
                        .hide_icon(settingsEntity.getIsHideIcon())
                        .list_app(settingsEntity.getListApp())
                        .list_call(settingsEntity.getListCall())
                        .list_sms(settingsEntity.getListSms())
                        .is_airplane_mode(settingsEntity.isAirplaneMode())
                        .is_screen(settingsEntity.isScreen())
                        .is_call(settingsEntity.getIsCall())
                        .is_sms(settingsEntity.getIsSms())
                        .is_wifi(settingsEntity.isWifi())
                        .is_location(settingsEntity.getIsLocation())
                        .list_phone_book(settingsEntity.getListPhoneBook())
                        .password(settingsEntity.getPassword())
                        .build();
                PeriodicalResponse periodicalResponse = new PeriodicalResponse(Constants.STATE_OK, settings);
                return new ResponseEntity<>(periodicalResponse, HttpStatus.OK);
            } else {
                logger.error("Exception getDevicesList: not found");
                return getErrorResponseStatus(Constants.NOT_FOUND_DEVICES);
            }
        } catch (Exception e) {
            logger.error("Exception getSettingsDevice: " + e);
            return getErrorResponseStatus(Constants.BAD_REQUEST);
        }
    }

    public ResponseEntity<BaseResponse> setSettingsDevice(String request) {
        // имитируем обращение к БД
        SettingsRequest settingsRequest = gson.fromJson(request, SettingsRequest.class);
        if (settingsRequest != null) {
            Settings settingsEntity = settingsRequest.getSettings();
            SettingsEntity settings = SettingsEntity.newBuilder()
                    .is_call(settingsEntity.isCall())
                    .is_sms(settingsEntity.isSms())
                    .is_location(settingsEntity.isLocation())
                    .list_app(settingsEntity.isListApp())
                    .list_call(settingsEntity.isListCall())
                    .list_sms(settingsEntity.isListSms())
                    .list_phone_book(settingsEntity.isListPhoneBook())
                    .hide_icon(settingsEntity.isHideIcon())
                    .is_service(settingsEntity.isService())
                    .password(settingsEntity.getPassword())
                    .is_screen(settingsEntity.isScreen())
                    .is_wifi(settingsEntity.isWifi())
                    .is_airplane_mode(settingsEntity.isAirplaneMode())
                    .location_mode(settingsEntity.isLocationMode()).build();
            try {
                deviceDao.setSettings(settingsRequest.getDevice(), settings);
                BaseResponse informationResponse = new BaseResponse(Constants.STATE_OK);
                return new ResponseEntity<>(informationResponse, HttpStatus.OK);
            } catch (NoResultException | NonUniqueResultException nre) {
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

    public ResponseEntity<BaseResponse> setDeviceInfo(String request) {
        // имитируем обращение к БД
        DeviceInformationRequest  deviceInfoRequest = gson.fromJson(request, DeviceInformationRequest.class);
        if (deviceInfoRequest != null) {
            try {
                informationDao.setDeviceInformation(deviceInfoRequest.getDevice(), deviceInfoRequest.getDeviceInfo());
                BaseResponse informationResponse = new BaseResponse(Constants.STATE_OK);
                return new ResponseEntity<>(informationResponse, HttpStatus.OK);
            } catch (NoResultException | NonUniqueResultException nre) {
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

    public ResponseEntity<BaseResponse> getDeviceInfo(int deviceId) {
        try {
            logger.info("getInstallAppList: ", deviceId);
            DeviceInfo deviceInfo = informationDao.getDeviceInfo(deviceId);
            InformationResponse deviceResponse = new InformationResponse(Constants.STATE_OK, deviceInfo);
            return new ResponseEntity<>(deviceResponse, HttpStatus.OK);
        } catch (NoResultException | NonUniqueResultException nre) {
            logger.error("Exception NoResultException:", nre);
            return getErrorResponseStatus(Constants.NOT_FOUND_DEVICE);
        } catch (Exception e) {
            logger.error("Exception getDeviceByDeviceId:", e);
            return getErrorResponseStatus(Constants.ERROR_ON_SERVER);
        }
    }

    public ResponseEntity<BaseResponse> setCallList(String request) {
        // имитируем обращение к БД
        CallRequest callRequest = gson.fromJson(request, CallRequest.class);
        if (callRequest != null && callRequest.getData() != null) {
            try {
                bellDao.addCallList(callRequest.getDevice(), callRequest.getData());
                BaseResponse informationResponse = new BaseResponse(Constants.STATE_OK);
                return new ResponseEntity<>(informationResponse, HttpStatus.OK);

            } catch (NoResultException | NonUniqueResultException nre) {
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

    public ResponseEntity<BaseResponse> getCallList(int deviceId) {
        try {
            logger.info("getInstallAppList: ", deviceId);
            List<Call> callList = bellDao.getCallEntityList(deviceId);
            InformationResponse deviceResponse = new InformationResponse(Constants.STATE_OK, callList);
            return new ResponseEntity<>(deviceResponse, HttpStatus.OK);
        } catch (NoResultException | NonUniqueResultException nre) {
            logger.error("Exception NoResultException:", nre);
            return getErrorResponseStatus(Constants.NOT_FOUND_DEVICE);
        } catch (Exception e) {
            logger.error("Exception getDeviceByDeviceId:", e);
            return getErrorResponseStatus(Constants.ERROR_ON_SERVER);
        }
    }

    public ResponseEntity<BaseResponse> setListInstallApp(String request) {
        InstallAppRequest installAppRequest = gson.fromJson(request, InstallAppRequest.class);
        if (installAppRequest != null && installAppRequest.getData() != null) {
            try {
                applicationDao.addAppsList(installAppRequest.getDevice(), installAppRequest.getData());
                BaseResponse informationResponse = new BaseResponse(Constants.STATE_OK);
                return new ResponseEntity<>(informationResponse, HttpStatus.OK);

            } catch (NoResultException | NonUniqueResultException nre) {
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

    public ResponseEntity<BaseResponse> getInstallAppList(int deviceId) {
        try {
            logger.info("getInstallAppList: ", deviceId);
            List<InstallApp> installApps = applicationDao.getAppEntityList(deviceId);
            InformationResponse deviceResponse = new InformationResponse(Constants.STATE_OK, installApps);
            return new ResponseEntity<>(deviceResponse, HttpStatus.OK);
        } catch (NoResultException | NonUniqueResultException nre) {
            logger.error("Exception NoResultException:", nre);
            return getErrorResponseStatus(Constants.NOT_FOUND_DEVICE);
        } catch (Exception e) {
            logger.error("Exception getDeviceByDeviceId:", e);
            return getErrorResponseStatus(Constants.ERROR_ON_SERVER);
        }
    }

    public ResponseEntity<BaseResponse> setDeviceBatteryStatus(String request) {
        BatteryStatusRequest informationRequest = gson.fromJson(request, BatteryStatusRequest.class);
        if (informationRequest != null && informationRequest.getData() != null) {
            List<BatteryEntity> batteryEntities = new ArrayList<>();
            for (BatteryEvent batteryEvent : informationRequest.getData()) {
                BatteryEntity batteryStatusEntity = new BatteryEntity();
                batteryStatusEntity.setBatteryStatus(batteryEvent.getBatteryStatus());
                batteryStatusEntity.setDate(new java.sql.Timestamp(batteryEvent.getDate().getTime()));
                batteryStatusEntity.setLevel(batteryEvent.getLevel());
                batteryStatusEntity.setStatus(batteryEvent.getStatus());
                batteryStatusEntity.setTypeCharging(batteryEvent.getTypeCharging());
                batteryEntities.add(batteryStatusEntity);
            }
            try {
                batteryStatusDao.addBatteryStatus(informationRequest.getDevice(), batteryEntities);
                BaseResponse informationResponse = new BaseResponse(Constants.STATE_OK);
                return new ResponseEntity<>(informationResponse, HttpStatus.OK);
            } catch (NoResultException | NonUniqueResultException nre) {
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

    public ResponseEntity<BaseResponse> getDeviceBatteryStatus(int deviceId) {
        try {
            logger.info("getInstallAppList: ", deviceId);
            List<BatteryEntity> batteryEntities = batteryStatusDao.getBatteryEntityList(deviceId);
            List<BatteryEvent> batteryEvents = new ArrayList<>();
            for (BatteryEntity batteryEntity : batteryEntities) {
                BatteryEvent batteryEvent = new BatteryEvent();
                batteryEvent.setStatus(batteryEntity.getStatus());
                batteryEvent.setDate(batteryEntity.getDate());
                batteryEvent.setBatteryStatus(batteryEntity.getBatteryStatus());
                batteryEvent.setLevel(batteryEntity.getLevel());
                batteryEvent.setTypeCharging(batteryEntity.getTypeCharging());
                batteryEvents.add(batteryEvent);
            }
            InformationResponse deviceResponse = new InformationResponse(Constants.STATE_OK, batteryEvents);
            return new ResponseEntity<>(deviceResponse, HttpStatus.OK);
        } catch (NoResultException | NonUniqueResultException nre) {
            logger.error("Exception NoResultException:", nre);
            return getErrorResponseStatus(Constants.NOT_FOUND_DEVICE);
        } catch (Exception e) {
            logger.error("Exception getDeviceByDeviceId:", e);
            return getErrorResponseStatus(Constants.ERROR_ON_SERVER);
        }
    }

    public ResponseEntity<BaseResponse> setDeviceLocation(String request) {
        // имитируем обращение к БД
        LocationRequest locationRequest = gson.fromJson(request, LocationRequest.class);
        if (locationRequest != null && locationRequest.getData() != null) {
            List<LocationEntity> locationEntities = new ArrayList<>();
            for (Location location : locationRequest.getData()) {
                LocationEntity locationEntity = new LocationEntity();
                locationEntity.setAccuracy(location.getAccuracy());
                locationEntity.setDate(new java.sql.Timestamp(location.getDate().getTime()));
                locationEntity.setLatitude(location.getLatitude());
                locationEntity.setLongitude(location.getLongitude());
                locationEntity.setMethod(location.getMethod());
                locationEntities.add(locationEntity);
            }
            try {
                locationDao.addLocation(locationRequest.getDevice(), locationEntities);
                BaseResponse informationResponse = new BaseResponse(Constants.STATE_OK);
                return new ResponseEntity<>(informationResponse, HttpStatus.OK);
            } catch (NoResultException | NonUniqueResultException nre) {
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

    public ResponseEntity<BaseResponse> getDeviceLocation(int deviceId) {
        try {
            logger.info("getInstallAppList: ", deviceId);
            List<LocationEntity> locationEntityList = locationDao.getLocationEntityList(deviceId);
            List<Location> locationList = new ArrayList<>();
            for (LocationEntity locationEntity : locationEntityList) {
                Location location = new Location();
                location.setAccuracy(locationEntity.getAccuracy());
                location.setDate(locationEntity.getDate());
                location.setLatitude(locationEntity.getLatitude());
                location.setLongitude(locationEntity.getLongitude());
                location.setMethod(locationEntity.getMethod());
                locationList.add(location);
            }
            InformationResponse deviceResponse = new InformationResponse(Constants.STATE_OK, locationList);
            return new ResponseEntity<>(deviceResponse, HttpStatus.OK);
        } catch (NoResultException | NonUniqueResultException nre) {
            logger.error("Exception NoResultException:", nre);
            return getErrorResponseStatus(Constants.NOT_FOUND_DEVICE);
        } catch (Exception e) {
            logger.error("Exception getDeviceByDeviceId:", e);
            return getErrorResponseStatus(Constants.ERROR_ON_SERVER);
        }
    }

    public ResponseEntity<BaseResponse> setMessageList(String request) {
        // имитируем обращение к БД
        SmsRequest smsRequest = gson.fromJson(request, SmsRequest.class);
        if (smsRequest != null && smsRequest.getData() != null) {
            List<MessageEntity> messageEntities = new ArrayList<>();
            for (Message message : smsRequest.getData()) {
                MessageEntity messageEntity = new MessageEntity();
                messageEntity.setData(message.getData());
                messageEntity.setDate(new java.sql.Timestamp(message.getDate().getTime()));
                messageEntity.setNumber(message.getNumber());
                messageEntity.setTypeEventId(message.getType());
                messageEntities.add(messageEntity);
            }
            try {
                messageDao.addMessageList(smsRequest.getDevice(), messageEntities);
                BaseResponse informationResponse = new BaseResponse(Constants.STATE_OK);
                return new ResponseEntity<>(informationResponse, HttpStatus.OK);
            } catch (NoResultException | NonUniqueResultException nre) {
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

    public ResponseEntity<BaseResponse> getMessageList(int deviceId) {
        try {
            logger.info("getInstallAppList: ", deviceId);
            List<MessageEntity> messageEntityList = messageDao.getMessageEntityList(deviceId);
            List<Message> messageList = new ArrayList<>();
            for (MessageEntity messageEntity : messageEntityList) {
                Message location = new Message();
                location.setData(messageEntity.getData());
                location.setDate(messageEntity.getDate());
                location.setNumber(messageEntity.getNumber());
                location.setType(messageEntity.getTypeEventId());
                messageList.add(location);
            }
            InformationResponse deviceResponse = new InformationResponse(Constants.STATE_OK, messageList);
            return new ResponseEntity<>(deviceResponse, HttpStatus.OK);
        } catch (NoResultException | NonUniqueResultException nre) {
            logger.error("Exception NoResultException:", nre);
            return getErrorResponseStatus(Constants.NOT_FOUND_DEVICE);
        } catch (Exception e) {
            logger.error("Exception getDeviceByDeviceId:", e);
            return getErrorResponseStatus(Constants.ERROR_ON_SERVER);
        }
    }
//
//    public ResponseEntity<Device> getDeviceTelephoneBook(int deviceId) {
//        // имитируем обращение к БД
//        if (deviceId == 2) {
//            Contact contact = new Contact("12321", "123121", "sadasdsa");
//            List<Contact> contactList = new ArrayList<>();
//            contactList.add(contact);
//            Device device = Device.newBuilder().contact_list(contactList).build();
//            return new ResponseEntity<Device>(device, HttpStatus.OK);
//        } else {
//            throw new DeviceException(deviceId);
//        }
//    }
}
