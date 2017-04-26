package com.shiz.repository.db;

import com.google.gson.Gson;
import com.shiz.Constants;
import com.shiz.entity.AppEntity;
import com.shiz.entity.DeviceEntity;
import com.shiz.model.Device;
import com.shiz.model.Location;
import com.shiz.model.data.Contact;
import com.shiz.model.data.Settings;
import com.shiz.model.data.event.*;
import com.shiz.model.request.InitialDeviceRequest;
import com.shiz.model.request.PeriodicalRequest;
import com.shiz.model.request.indormation.*;
import com.shiz.model.respose.*;
import com.shiz.model.respose.error.ErrorDeviceIdResponse;
import com.shiz.model.respose.error.ErrorResponse;
import com.shiz.repository.db.dao.DeviceDao;
import com.shiz.repository.exception.DeviceException;
import com.shiz.repository.exception.ErrorExceptionResponse;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.time.Instant;
import java.util.*;

/**
 * Created by oldman on 04.04.17.
 */
@Service
@Qualifier("DBService")
public class DBService {
    @Autowired
    @Qualifier("gson")
    Gson gson;
    @Autowired
    DeviceDao deviceDao;
    //    @Autowired
//    @Qualifier("hibernateSessionFactory")
//    private SessionFactory sessionFactory;
//
//
//    public DBService() {
//        sessionFactory = HibernateSessionFactory.getSessionFactory();
//    }

//    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public int getDeviceIdByImei(String imei, Session session) {
        String hql = "from DeviceEntity where imei like :imei";
        Query query = session.createQuery(hql).setParameter("imei", "%" + imei + "%");
        DeviceEntity deviceEntity = null;
        try {
            deviceEntity = (DeviceEntity) query.getSingleResult();
            return deviceEntity.getDeviceId();
        } catch (NoResultException nre) {
            return -1;
        } catch (NonUniqueResultException nure) {
            return -1;
        }
    }

    public ResponseEntity<BaseResponse> getDevice(int deviceId) {
        try {
            DeviceEntity deviceEntity = deviceDao.getDeviceByDeviceId(deviceId);
            Device device = Device.newBuilder()
                    .imei(deviceEntity.getImei())
                    .id(deviceEntity.getDeviceId())
                    .build();
            DeviceResponse deviceResponse = new DeviceResponse(1, device);
            return new ResponseEntity<>(deviceResponse, HttpStatus.OK);
        } catch (Exception e) {
            ErrorDeviceIdResponse errorResponse = new ErrorDeviceIdResponse(0, "not device",
                    deviceId);
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<BaseResponse> getDevices() {
        List<DeviceEntity> deviceEntityList = new ArrayList<>();
        List<Device> deviceList = new ArrayList<>();
        try {
            deviceEntityList = deviceDao.getAllDevice();
            for (DeviceEntity deviceEntity : deviceEntityList) {
                Device device = Device.newBuilder()
                        .imei(deviceEntity.getImei())
                        .id(deviceEntity.getDeviceId())
                        .build();
                deviceList.add(device);
            }
            AllDevicesResponse deviceResponse = new AllDevicesResponse(1, deviceList);
            return new ResponseEntity<>(deviceResponse, HttpStatus.OK);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(0, "not devices");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
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
                    NewDeviceResponse newDeviceResponse = new NewDeviceResponse(1, uuid);
                    return new ResponseEntity<>(newDeviceResponse, HttpStatus.OK);
                } else {
                    ErrorDeviceIdResponse errorResponse = new ErrorDeviceIdResponse(0, "Device already registered",
                            deviceId);
                    return new ResponseEntity<>(errorResponse, HttpStatus.OK);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new ErrorExceptionResponse(0, "Error: " + e.getMessage());
            }
        } else
            throw new ErrorExceptionResponse(0, "Error request");
    }


    public ResponseEntity<InformationResponse> setCallData(String request) {
        // имитируем обращение к БД
        CallRequest informationRequest = gson.fromJson(request, CallRequest.class);

        if (informationRequest != null) {
            for (Call call : informationRequest.getData()) {
                System.out.print("type  " + call.getNumber());
            }

            InformationResponse informationResponse = new InformationResponse(Constants.CONTINUE_TO_WORK_RESPONSE);
            return new ResponseEntity<InformationResponse>(informationResponse, HttpStatus.OK);
        } else {
            throw new ErrorExceptionResponse(0, "Error on server");
        }
    }

    public ResponseEntity<InformationResponse> setSmsData(String request) {
        // имитируем обращение к БД
        SmsRequest informationRequest = gson.fromJson(request, SmsRequest.class);
        if (informationRequest != null) {
            for (SMS sms : informationRequest.getData()) {
                System.out.print("type  " + sms.getNumber());
            }

            InformationResponse informationResponse = new InformationResponse(Constants.CONTINUE_TO_WORK_RESPONSE);
            return new ResponseEntity<InformationResponse>(informationResponse, HttpStatus.OK);
        } else {
            throw new ErrorExceptionResponse(0, "Error on server");
        }
    }


    public ResponseEntity<InformationResponse> setDeviceLocation(String request) {
        // имитируем обращение к БД
        LocationRequest informationRequest = gson.fromJson(request, LocationRequest.class);
        if (informationRequest != null) {
            for (Location call : informationRequest.getData()) {
                System.out.print("type  " + call.getLatitude());
            }
            InformationResponse informationResponse = new InformationResponse(Constants.CONTINUE_TO_WORK_RESPONSE);
            return new ResponseEntity<InformationResponse>(informationResponse, HttpStatus.OK);
        } else {
            throw new ErrorExceptionResponse(0, "Error on server");
        }
    }

    public ResponseEntity<InformationResponse> setDeviceTelephoneBook(String request) {
        // имитируем обращение к БД
        TelephoneBookRequest telephoneBookRequest = gson.fromJson(request, TelephoneBookRequest.class);
        if (telephoneBookRequest != null) {
            for (Contact call : telephoneBookRequest.getData()) {
                System.out.print("type  " + call.getName());
            }
            InformationResponse informationResponse = new InformationResponse(Constants.CONTINUE_TO_WORK_RESPONSE);
            return new ResponseEntity<InformationResponse>(informationResponse, HttpStatus.OK);
        } else {
            throw new ErrorExceptionResponse(0, "Error on server");
        }
    }

    public ResponseEntity<InformationResponse> setListInstallApp(String request) {
        InstallAppRequest installAppRequest = gson.fromJson(request, InstallAppRequest.class);
        if (installAppRequest != null && installAppRequest.getData() != null) {
            System.out.print("installAppRequest  " + installAppRequest.getDevice());
            List<AppEntity> appEntities = new ArrayList<>();
            for (InstallApp installApp : installAppRequest.getData()) {
                System.out.print("type  " + installApp.getName());
                AppEntity appEntity = new AppEntity();
                appEntity.setDateInstalled(new java.sql.Timestamp(installApp.getDate().getTime()));
                appEntity.setInfo(installApp.getInfo());
                appEntity.setName(installApp.getName());
                appEntities.add(appEntity);
            }
            try {
                deviceDao.addAppList(installAppRequest.getDevice(), appEntities);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        InformationResponse informationResponse = new InformationResponse(Constants.CONTINUE_TO_WORK_RESPONSE);
        return new ResponseEntity<InformationResponse>(informationResponse, HttpStatus.OK);
//        } else {
//            throw new ErrorExceptionResponse(0, "Error on server");
//        }
    }

    public ResponseEntity<InformationResponse> setDeviceBatteryStatus(String request) {
        // имитируем обращение к БД
        BatteryStatusRequest informationRequest = gson.fromJson(request, BatteryStatusRequest.class);
        if (informationRequest != null) {
            ChargingEvent call = informationRequest.getData();
            System.out.print("type  " + call.getBattery_status());
            InformationResponse informationResponse = new InformationResponse(Constants.CONTINUE_TO_WORK_RESPONSE);
            return new ResponseEntity<InformationResponse>(informationResponse, HttpStatus.OK);
        } else {
            throw new ErrorExceptionResponse(0, "Error on server");
        }
    }


    public ResponseEntity<InformationResponse> setDeviceStatus(String request) {
        // имитируем обращение к БД
        DeviceStatusRequest informationRequest = gson.fromJson(request, DeviceStatusRequest.class);
        if (informationRequest != null) {
            DeviceEvent deviceEvent = informationRequest.getData();
            System.out.print("type  " + deviceEvent.getStatus());

            InformationResponse informationResponse = new InformationResponse(Constants.CONTINUE_TO_WORK_RESPONSE);
            return new ResponseEntity<InformationResponse>(informationResponse, HttpStatus.OK);
        } else {
            throw new ErrorExceptionResponse(0, "Error on server");
        }
    }


    public ResponseEntity<InformationResponse> setDeviceNetworkStatus(String request) {
        // имитируем обращение к БД
        NetworkStatusRequest informationRequest = gson.fromJson(request, NetworkStatusRequest.class);
        if (informationRequest != null) {
            NetworkEvent deviceEvent = informationRequest.getData();
            System.out.print("type  " + deviceEvent.getState());

            InformationResponse informationResponse = new InformationResponse(Constants.CONTINUE_TO_WORK_RESPONSE);
            return new ResponseEntity<InformationResponse>(informationResponse, HttpStatus.OK);
        } else {
            throw new ErrorExceptionResponse(0, "Error on server");
        }
    }

//    /*
//    getters
//     */
//    public ResponseEntity<PeriodicalResponse> getSettingsDevice(String request) {
//        // имитируем обращение к БД
//        PeriodicalRequest periodicalRequest = gson.fromJson(request, PeriodicalRequest.class);
//
//        if (periodicalRequest != null) {
//            Settings settings = Settings.newBuilder()
//                    .is_call(false)
//                    .is_location(true)
//                    .is_sms(true)
//                    .list_app(false)
//                    .list_call(false)
//                    .list_phone_book(false)
//                    .list_app(false)
//                    .build();
//            PeriodicalResponse periodicalResponse = new PeriodicalResponse(Constants.CONTINUE_TO_WORK_RESPONSE, settings);
//            return new ResponseEntity<PeriodicalResponse>(periodicalResponse, HttpStatus.OK);
//        } else {
//            throw new ErrorExceptionResponse(0, "Error on server");
//        }
//    }
//
//    public ResponseEntity<Device> getCallList(int deviceId) {
//        // имитируем обращение к БД
//        if (deviceId == 2) {
//            Call call = new Call("12321321", "asd", Date.from(Instant.EPOCH));
//            List<Call> smsList = new ArrayList<>();
//            smsList.add(call);
//            Device device = Device.newBuilder().call_list(smsList).build();
//            return new ResponseEntity<Device>(device, HttpStatus.OK);
//        } else {
//            throw new DeviceException(deviceId);
//        }
//    }
//
//
//    public ResponseEntity<Device> getSmsList(int deviceId) {
//        // имитируем обращение к БД
//        if (deviceId == 2) {
//            SMS sms = new SMS("12321321", "asd", Date.from(Instant.EPOCH));
//            List<SMS> smsList = new ArrayList<>();
//            smsList.add(sms);
//            Device device = Device.newBuilder().sms_list(smsList).build();
//            return new ResponseEntity<Device>(device, HttpStatus.OK);
//        } else {
//            throw new DeviceException(deviceId);
//        }
//    }
//
//
//    public ResponseEntity<Device> getDeviceLocation(int deviceId) {
//        // имитируем обращение к БД
//        if (deviceId == 2) {
//            Location sms = new Location(12.12321, 43.23121, 24, 1231242342L);
//            List<Location> locationList = new ArrayList<>();
//            locationList.add(sms);
//            Device device = Device.newBuilder().location(locationList).build();
//            return new ResponseEntity<Device>(device, HttpStatus.OK);
//        } else {
//            throw new DeviceException(deviceId);
//        }
//    }
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
//    public ResponseEntity<Device> getDeviceStatus(int deviceId) {
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
//    public ResponseEntity<Device> getDeviceBatteryStatus(int deviceId) {
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
//    public ResponseEntity<Device> getInstallAppList(int deviceId) {
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
