package com.shiz.repository.network;

import com.shiz.model.respose.BaseResponse;
import com.shiz.repository.db.DBService;
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
    @Qualifier("DBService")
    private DBService dbService;

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

    @RequestMapping(value = "/device/{id}/rm", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<BaseResponse> rmDeviceById(@PathVariable int id) {
        return dbService.removeDeviceByDeviceId(id);
    }

    @RequestMapping(value = "/device/all_rm", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<BaseResponse> rmAllDevice() {
        return dbService.removeAllDevices();
    }

    // getters
    @RequestMapping(value = "/sync", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<BaseResponse> onSyncMessage(@RequestBody String request) {
        return dbService.getSettingsByDevice(request);
    }

    @RequestMapping(value = "device/{id}/settings", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<BaseResponse> getSettings(@PathVariable int id) {
        return dbService.getSettingsByControlPoint(id);
    }

    @RequestMapping(value = "device/{id}/apps", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<BaseResponse> getInstallAppList(@PathVariable int id) {
        System.out.println("getInstallAppList deviceId " + id);
        return dbService.getInstallAppList(id);
    }

    @RequestMapping(value = "device/{id}/calls", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<BaseResponse> getCallList(@PathVariable int id) {
        System.out.println("getCallList deviceId " + id);
        return dbService.getCallList(id);
    }

    @RequestMapping(value = "device/{id}/contacts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<BaseResponse> getContactList(@PathVariable String id) {
        System.out.println("getContactList deviceId " + id);
        return dbService.getContactList(Integer.valueOf(id));
    }

    @RequestMapping(value = "device/{id}/status", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<BaseResponse> getDeviceStatus(@PathVariable int id) {
        System.out.println("getDeviceStatus " + id);
        return dbService.getDeviceStatusList(id);
    }

    @RequestMapping(value = "device/{id}/info", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<BaseResponse> getDeviceInfo(@PathVariable int id) {
        System.out.println("getData " + id);
        return dbService.getDeviceInfo(id);
    }

    @RequestMapping(value = "device/{id}/locations", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<BaseResponse> getDeviceLocation(@PathVariable int id) {
        System.out.println("getLocationList " + id);
        return dbService.getLocationList(id);
    }

    @RequestMapping(value = "device/{id}/messages", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<BaseResponse> getMessageList(@PathVariable String id) {
        System.out.println("getMessageList " + id);
        return dbService.getMessageList(Integer.valueOf(id));
    }

    @RequestMapping(value = "device/{id}/network", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<BaseResponse> getNetworkStatusList(@PathVariable int id) {
        System.out.println("getNetworkStatusList " + id);
        return dbService.getNetworkStatusList(id);
    }

    @RequestMapping(value = "device/{id}/services", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<BaseResponse> getServiceEvent(@PathVariable int id) {
        System.out.println("getServiceEventList " + id);
        return dbService.getServiceEventList(id);
    }

    @RequestMapping(value = "device/{id}/battery", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<BaseResponse> getDeviceBattery(@PathVariable int id) {
        System.out.println("getDeviceBattery");
        return dbService.getBatteryStatusList(id);
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<BaseResponse> getUser(@RequestBody String request) {
        System.out.println("getUser");
        return dbService.getUser(request);
    }
    /*
    setter
     */
    @RequestMapping(value = "/init", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<BaseResponse> setNewDevice(@RequestBody String request) {
        return dbService.setNewDevice(request);
    }

    @RequestMapping(value = "/app", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<BaseResponse> setDeviceInstallApp(@RequestBody String request) {
        return dbService.setInstallAppList(request);
    }

    @RequestMapping(value = "/battery", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<BaseResponse> setDeviceBattery(@RequestBody String request) {
        return dbService.setBatteryStatus(request);
    }

    @RequestMapping(value = "/call", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<BaseResponse> setCallListDevice(@RequestBody String request) {
        return dbService.setCallList(request);
    }

    @RequestMapping(value = "/contact", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<BaseResponse> setDeviceTelephoneBook(@RequestBody String request) {
        return dbService.setContactList(request);
    }

    @RequestMapping(value = "/status", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<BaseResponse> setDeviceStatus(@RequestBody String request) {
        return dbService.setDeviceStatus(request);
    }

    @RequestMapping(value = "/info", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<BaseResponse> setDeviceInfo(@RequestBody String request) {
        return dbService.setDeviceInfo(request);
    }

    @RequestMapping(value = "/location", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<BaseResponse> setLocationList(@RequestBody String request) {
        return dbService.setLocationList(request);
    }

    @RequestMapping(value = "/messages", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<BaseResponse> setMessageList(@RequestBody String request) {
        return dbService.setMessageList(request);
    }

    @RequestMapping(value = "/network", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<BaseResponse> setNetworkStatusList(@RequestBody String request) {
        return dbService.setNetworkStatusList(request);
    }

    @RequestMapping(value = "/event", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<BaseResponse> setServiceEventList(@RequestBody String request) {
        return dbService.setServiceEventList(request);
    }

    @RequestMapping(value = "/setting", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<BaseResponse> setSettingsDevice(@RequestBody String request) {
        return dbService.setSettings(request);
    }

    @RequestMapping(value = "/new_user", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<BaseResponse> addUser(@RequestBody String request) {
        return dbService.addUser(request);
    }

}
