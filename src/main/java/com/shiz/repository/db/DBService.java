package com.shiz.repository.db;

import com.shiz.model.respose.BaseResponse;
import org.springframework.http.ResponseEntity;

/**
 * Created by oldman on 28.04.17.
 */
public interface DBService {

    ResponseEntity<BaseResponse> setNewDevice(String request);
    ResponseEntity<BaseResponse> getDeviceByDeviceId(int deviceId);
    ResponseEntity<BaseResponse> getDevicesList();
    ResponseEntity<BaseResponse> removeDeviceByDeviceId(int deviceId);
    ResponseEntity<BaseResponse> removeAllDevices();

    //information
    ResponseEntity<BaseResponse> setCallData(String request);
    ResponseEntity<BaseResponse> setSmsData(String request);
    ResponseEntity<BaseResponse> setDeviceLocation(String request);
    ResponseEntity<BaseResponse> setDeviceTelephoneBook(String request);
    ResponseEntity<BaseResponse> setListInstallApp(String request);
    ResponseEntity<BaseResponse> setDeviceBatteryStatus(String request);
    ResponseEntity<BaseResponse> setDeviceStatus(String request);
    ResponseEntity<BaseResponse> setDeviceNetworkStatus(String request);
}
