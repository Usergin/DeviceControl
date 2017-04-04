package com.shiz.repository.db;

import com.shiz.model.Device;
import com.shiz.model.Location;
import com.shiz.model.Response;
import com.shiz.repository.exception.DeviceException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oldman on 04.04.17.
 */
@Component
public class DBService {

    public Device getDevice(int deviceId) {
        // имитируем обращение к БД
        if (deviceId == 2) {
            return new Device(deviceId, "123123", "Htc One", "123serial", new Location(12.12321, 43.23121, 24, 1231242342L));
        } else {
            throw new DeviceException(deviceId);
        }
    }

    public List<Device> getDevices() {
    List<Device> deviceList = new ArrayList<>();
        // имитируем обращение к БД
       for(int i=0; i<10; i++) {
          deviceList.add(new Device(i, "123123", "Htc One " + i, "123serial", new Location(12.12321, 43.23121, 24, 1231242342L)));
       }
        return  deviceList;
    }

    public Device getLocationDevice(int deviceId) {
        // имитируем обращение к БД
        if (deviceId == 2) {
            return new Device(deviceId, "123123", "Htc One", "123serial", new Location(12.12321, 43.23121, 24, 1231242342L));
        } else {
            throw new DeviceException(deviceId);
        }
    }

    public Response setLocationDevice(int deviceId) {
        // имитируем обращение к БД
        if (deviceId == 2) {
            return new Response(deviceId, "Success");
        } else {
            throw new DeviceException(deviceId);
        }
    }
}
