package com.shiz.model.respose;

/**
 * Created by oldman on 05.04.17.
 */
public class NewDeviceResponse {
    private int code;
    private int device;

    public NewDeviceResponse(int code, int device) {
        this.code = code;
        this.device = device;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getDevice() {
        return device;
    }

    public void setDevice(int device) {
        this.device = device;
    }
}
