package com.shiz.repository.db;

import com.google.gson.Gson;
import com.shiz.Constants;
import com.shiz.entity.AppEntity;
import com.shiz.entity.BatteryStatusEntity;
import com.shiz.entity.DeviceEntity;
import com.shiz.model.Device;
import com.shiz.model.Location;
import com.shiz.model.data.Contact;
import com.shiz.model.data.event.*;
import com.shiz.model.request.InitialDeviceRequest;
import com.shiz.model.request.indormation.*;
import com.shiz.model.respose.*;
import com.shiz.model.respose.error.ErrorDeviceIdResponse;
import com.shiz.model.respose.error.ErrorResponse;
import com.shiz.repository.db.dao.DeviceDao;
import com.shiz.repository.exception.DeviceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
    DeviceDao deviceDao;
    //    @Autowired
//    @Qualifier("hibernateSessionFactory")
//    private SessionFactory sessionFactory;

    public ResponseEntity<BaseResponse> getDeviceByDeviceId(int deviceId) {
        try {
            DeviceEntity deviceEntity = deviceDao.getDeviceByDeviceId(deviceId);
            Device device = Device.newBuilder()
                    .imei(deviceEntity.getImei())
                    .id(deviceEntity.getDeviceId())
                    .build();
            DeviceResponse deviceResponse = new DeviceResponse(Constants.STATE_OK, device);
            return new ResponseEntity<>(deviceResponse, HttpStatus.OK);
        } catch (Exception e) {
            return getErrorResponseStatus(Constants.NOT_FOUND_DEVICES);
        }
    }

    public ResponseEntity<BaseResponse> getDevicesList() {
        List<DeviceEntity> deviceEntityList = new ArrayList<>();
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
                return getErrorResponseStatus(Constants.NOT_FOUND_DEVICES);
            }
        } catch (Exception e) {
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

    public ResponseEntity<BaseResponse> setCallData(String request) {
        // имитируем обращение к БД
        CallRequest informationRequest = gson.fromJson(request, CallRequest.class);

        if (informationRequest != null) {
            for (Call call : informationRequest.getData()) {
                System.out.print("type  " + call.getNumber());
            }

            InformationResponse informationResponse = new InformationResponse(Constants.STATE_OK);
            return new ResponseEntity<>(informationResponse, HttpStatus.OK);
        } else {
            return getErrorResponseStatus(Constants.BAD_REQUEST);
        }
    }

    public ResponseEntity<BaseResponse> setSmsData(String request) {
        // имитируем обращение к БД
        SmsRequest informationRequest = gson.fromJson(request, SmsRequest.class);
        if (informationRequest != null) {
            for (SMS sms : informationRequest.getData()) {
                System.out.print("type  " + sms.getNumber());
            }
            InformationResponse informationResponse = new InformationResponse(Constants.STATE_OK);
            return new ResponseEntity<>(informationResponse, HttpStatus.OK);
        } else {
            return getErrorResponseStatus(Constants.BAD_REQUEST);
        }
    }


    public ResponseEntity<BaseResponse> setDeviceLocation(String request) {
        // имитируем обращение к БД
        LocationRequest informationRequest = gson.fromJson(request, LocationRequest.class);
        if (informationRequest != null) {
            for (Location call : informationRequest.getData()) {
                System.out.print("type  " + call.getLatitude());
            }
            InformationResponse informationResponse = new InformationResponse(Constants.STATE_OK);
            return new ResponseEntity<>(informationResponse, HttpStatus.OK);
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
            InformationResponse informationResponse = new InformationResponse(Constants.STATE_OK);
            return new ResponseEntity<>(informationResponse, HttpStatus.OK);
        } else {
            return getErrorResponseStatus(Constants.BAD_REQUEST);
        }
    }

    public ResponseEntity<BaseResponse> setListInstallApp(String request) {
        InstallAppRequest installAppRequest = gson.fromJson(request, InstallAppRequest.class);
        if (installAppRequest != null && installAppRequest.getData() != null) {
            List<AppEntity> appEntities = new ArrayList<>();
            for (InstallApp installApp : installAppRequest.getData()) {
                AppEntity appEntity = new AppEntity();
                appEntity.setDateInstalled(new java.sql.Timestamp(installApp.getDate().getTime()));
                appEntity.setInfo(installApp.getInfo());
                appEntity.setName(installApp.getName());
                appEntities.add(appEntity);
            }
            try {
                int device = deviceDao.addAppList(installAppRequest.getDevice(), appEntities);
                return getResponseStatus(device);
            } catch (Exception e) {
                return getErrorResponseStatus(Constants.NOT_FOUND_DEVICE);
            }
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

    private ResponseEntity<BaseResponse> getResponseStatus(int device) {
        if (device == -1) {
            ErrorResponse errorResponse = new ErrorResponse(Constants.STATE_ERROR, Constants.NOT_FOUND_DEVICE);
            return new ResponseEntity<>(errorResponse, HttpStatus.OK);
        } else if (device == 0) {
            ErrorResponse errorResponse = new ErrorResponse(Constants.STATE_ERROR, Constants.DATA_NOT_AVAILABLE);
            return new ResponseEntity<>(errorResponse, HttpStatus.OK);
        } else {
            InformationResponse informationResponse = new InformationResponse(Constants.STATE_OK);
            return new ResponseEntity<>(informationResponse, HttpStatus.OK);
        }
    }

    public ResponseEntity<BaseResponse> setDeviceBatteryStatus(String request) {
        // имитируем обращение к БД
        BatteryStatusRequest informationRequest = gson.fromJson(request, BatteryStatusRequest.class);
        if (informationRequest != null && informationRequest.getData() != null) {
            ChargingEvent chargingEvent = informationRequest.getData();
            BatteryStatusEntity batteryStatusEntity = new BatteryStatusEntity();
            batteryStatusEntity.setBatteryStatus(chargingEvent.getBattery_status());
            batteryStatusEntity.setDate(new java.sql.Timestamp(chargingEvent.getDate().getTime()));
            batteryStatusEntity.setLevel(chargingEvent.getLevel());
            batteryStatusEntity.setStatus(chargingEvent.getStatus());
            batteryStatusEntity.setTypeCharging(chargingEvent.getType_charging());

            try {
                int device = deviceDao.addBatteryStatus(informationRequest.getDevice(), batteryStatusEntity);
                return getResponseStatus(device);
            } catch (Exception e) {
                return getErrorResponseStatus(Constants.NOT_FOUND_DEVICE);
            }
        } else {
            return getErrorResponseStatus(Constants.BAD_REQUEST);
        }
    }


    public ResponseEntity<BaseResponse> setDeviceStatus(String request) {
        // имитируем обращение к БД
        DeviceStatusRequest informationRequest = gson.fromJson(request, DeviceStatusRequest.class);
        if (informationRequest != null) {
            DeviceEvent deviceEvent = informationRequest.getData();
            System.out.print("type  " + deviceEvent.getStatus());

            InformationResponse informationResponse = new InformationResponse(Constants.STATE_OK);
            return new ResponseEntity<>(informationResponse, HttpStatus.OK);
        } else {
            ErrorResponse errorResponse = new ErrorResponse(Constants.STATE_ERROR, Constants.BAD_REQUEST);
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }


    public ResponseEntity<BaseResponse> setDeviceNetworkStatus(String request) {
        // имитируем обращение к БД
        NetworkStatusRequest informationRequest = gson.fromJson(request, NetworkStatusRequest.class);
        if (informationRequest != null) {
            NetworkEvent deviceEvent = informationRequest.getData();
            System.out.print("type  " + deviceEvent.getState());

            InformationResponse informationResponse = new InformationResponse(Constants.STATE_OK);
            return new ResponseEntity<>(informationResponse, HttpStatus.OK);
        } else {
            ErrorResponse errorResponse = new ErrorResponse(Constants.STATE_ERROR, Constants.BAD_REQUEST);
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
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
//            PeriodicalResponse periodicalResponse = new PeriodicalResponse(Constants.STATE_OK, settings);
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
