package com.shiz.model.respose;

import com.shiz.model.Device;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oldman on 24.04.17.
 */
public class AllDevicesResponse extends BaseResponse {
    List<Device> deviceList = new ArrayList<>();

    public AllDevicesResponse(int code, List<Device> deviceList) {
        super(code);
        this.deviceList = deviceList;
    }

    public List<Device> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(List<Device> deviceList) {
        this.deviceList = deviceList;
    }
}
