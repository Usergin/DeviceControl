package com.shiz.repository.network;

import com.shiz.model.respose.BaseResponse;
import com.shiz.repository.db.DBServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by oldman on 04.04.17.
 */
@RestController
public class DeviceController {
    @Autowired
    @Qualifier("DBServiceImpl")
    private DBServiceImpl dbService;

    @RequestMapping(value = "/devices", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<BaseResponse> getDevices() {
        return dbService.getDevicesList();
    }

    @RequestMapping(value = "/device/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<BaseResponse> getDeviceById(@PathVariable int id) {
        return dbService.getDeviceByDeviceId(id);
    }

    /*
    getter
     */

//    @RequestMapping(value = "/sync", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public ResponseEntity<PeriodicalResponse> onSyncMessage(@RequestBody String request) {
//        return dbService.getSettingsDevice(request);
//    }
//
//    @RequestMapping(value = "device/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public Device getDeviceByDeviceId(@PathVariable String id) {
//        System.out.println("deviceId " + id);
//        return dbService.getDeviceByDeviceId(Integer.valueOf(id));
//    }
//
//    @RequestMapping(value = "device/{id}/call_list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public ResponseEntity<Device> getDeviceCallList(@PathVariable String id) {
//        System.out.println("getDeviceSmsList deviceId " + id);
//        return dbService.getCallList(Integer.valueOf(id));
//    }
//
//    @RequestMapping(value = "device/{id}/sms_list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public ResponseEntity<Device> getDeviceSmsList(@PathVariable String id) {
//        System.out.println("getDeviceSmsList deviceId " + id);
//        return dbService.getSmsList(Integer.valueOf(id));
//    }
//
//    @RequestMapping(value = "device/{id}/location_list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public ResponseEntity<Device> getDeviceLocation(@PathVariable String id) {
//        System.out.println("getDeviceSmsList deviceId " + id);
//        return dbService.getDeviceLocation(Integer.valueOf(id));
//    }
//
//    @RequestMapping(value = "device/{id}/app_list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public ResponseEntity<Device> getInstallAppList(@PathVariable String id) {
//        System.out.println("getDeviceSmsList deviceId " + id);
//        return dbService.getInstallAppList(Integer.valueOf(id));
//    }
//
//    @RequestMapping(value = "device/{id}/telbook", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public ResponseEntity<Device> getDeviceTelephoneBook(@PathVariable String id) {
//        System.out.println("getDeviceSmsList deviceId " + id);
//        return dbService.getDeviceTelephoneBook(Integer.valueOf(id));
//    }
//
//    @RequestMapping(value = "device/{id}/status", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public ResponseEntity<Device> getDeviceStatus(@PathVariable String id) {
//        System.out.println("getDeviceBatteryStatus deviceId " + id);
//        return dbService.getDeviceStatus(Integer.valueOf(id));
//    }
//
//    @RequestMapping(value = "device/{id}/network", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public ResponseEntity<Device> getDeviceNetworkStatus(@PathVariable String id) {
//        System.out.println("getDeviceBatteryStatus deviceId " + id);
//        return dbService.getDeviceNetworkStatus(Integer.valueOf(id));
//    }
//
//    @RequestMapping(value = "device/{id}/battery", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public ResponseEntity<Device> getDeviceBatteryStatus(@PathVariable String id) {
//        System.out.println("getDeviceBatteryStatus deviceId " + id);
//        return dbService.getDeviceBatteryStatus(Integer.valueOf(id));
//    }
    /*
    setter
     */
    @RequestMapping(value = "/init", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<BaseResponse> setNewDevice(@RequestBody String request) {
          return dbService.setNewDevice(request);
    }

    @RequestMapping(value = "/call", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<BaseResponse> setCallListDevice(@RequestBody String request) {
        return dbService.setCallData(request);
    }

    @RequestMapping(value = "/sms", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<BaseResponse> setSmsListDevice(@RequestBody String request) {
        return dbService.setSmsData(request);
    }

    @RequestMapping(value = "/location", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<BaseResponse> setDeviceLocation(@RequestBody String request) {
        return dbService.setDeviceLocation(request);
    }

    @RequestMapping(value = "/telbook", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<BaseResponse> setDeviceTelephoneBook(@RequestBody String request) {
        return dbService.setDeviceTelephoneBook(request);
    }

    @RequestMapping(value = "/app", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<BaseResponse> setDeviceInstallApp(@RequestBody String request) {
        return dbService.setListInstallApp(request);
    }

    @RequestMapping(value = "/service/status", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<BaseResponse> setDeviceStatus(@RequestBody String request) {
        return dbService.setDeviceStatus(request);
    }

    @RequestMapping(value = "/service/battery", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<BaseResponse>  setDeviceBatteryState(@RequestBody String request) {
        return dbService.setDeviceBatteryStatus(request);
    }

    @RequestMapping(value = "/service/network", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<BaseResponse>  setList(@RequestBody String request) {
        return dbService.setDeviceNetworkStatus(request);
    }
}
