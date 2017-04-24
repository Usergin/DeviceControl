package com.shiz.model.respose;

import com.shiz.model.Device;

/**
 * Created by oldman on 24.04.17.
 */
public class DeviceResponse extends BaseResponse{
    Device device;

    public DeviceResponse(int code, Device device) {
        super(code);
        this.device = device;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }
}
