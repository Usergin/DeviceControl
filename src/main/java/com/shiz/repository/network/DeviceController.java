package com.shiz.repository.network;

import com.shiz.model.Device;
import com.shiz.repository.db.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by oldman on 04.04.17.
 */
@RestController
public class DeviceController {

    private final DBService dbService;

    @Autowired
    public DeviceController(DBService dbService) {
        this.dbService = dbService;
    }
    @RequestMapping(value = "/devices",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Device> getDevices() {
        return dbService.getDevices();
    }

    @RequestMapping(value = "device/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Device getDevice(@PathVariable String id) {
        System.out.println("deviceId " + id);
        return dbService.getDevice(Integer.valueOf(id));
    }

}
