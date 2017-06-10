package com.shiz.repository.db;

import com.shiz.model.respose.BaseResponse;
import org.springframework.http.ResponseEntity;

/**
 * Created by oldman on 28.04.17.
 */
public interface DBService {

    ResponseEntity<BaseResponse> setNewDevice(String request);

    ResponseEntity<BaseResponse> getDeviceByDeviceId(int deviceId);

    ResponseEntity<BaseResponse> getDeviceByImei(String imei);

    ResponseEntity<BaseResponse> getDevicesList();

    ResponseEntity<BaseResponse> removeDeviceByDeviceId(int deviceId);

    ResponseEntity<BaseResponse> removeAllDevices();

    //setters
    ResponseEntity<BaseResponse> setInstallAppList(String request);

    ResponseEntity<BaseResponse> setBatteryStatus(String request);

    ResponseEntity<BaseResponse> setCallList(String request);

    ResponseEntity<BaseResponse> setContactList(String request);

    ResponseEntity<BaseResponse> setDeviceStatus(String request);

    ResponseEntity<BaseResponse> setDeviceInfo(String request);

    ResponseEntity<BaseResponse> setLocationList(String request);

    ResponseEntity<BaseResponse> setMessageList(String request);

    ResponseEntity<BaseResponse> setNetworkStatusList(String request);

    ResponseEntity<BaseResponse> setServiceEventList(String request);

    ResponseEntity<BaseResponse> setSettings(String request);

    ResponseEntity<BaseResponse> addUser(String request);
    //getters
    ResponseEntity<BaseResponse> getInstallAppList(int deviceId);

    ResponseEntity<BaseResponse> getBatteryStatusList(int deviceId);

    ResponseEntity<BaseResponse> getCallList(int deviceId);

    ResponseEntity<BaseResponse> getContactList(int deviceId);

    ResponseEntity<BaseResponse> getDeviceStatusList(int deviceId);

    ResponseEntity<BaseResponse> getDeviceInfo(int deviceId);

    ResponseEntity<BaseResponse> getLocationList(int deviceId);

    ResponseEntity<BaseResponse> getMessageList(int deviceId);

    ResponseEntity<BaseResponse> getNetworkStatusList(int deviceId);

    ResponseEntity<BaseResponse> getServiceEventList(int deviceId);

    ResponseEntity<BaseResponse> getSettingsByDevice(String request);

    ResponseEntity<BaseResponse> getSettingsByControlPoint(int deviceId);

    ResponseEntity<BaseResponse> getUser(String request);

}
