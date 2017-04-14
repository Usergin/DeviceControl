package com.shiz.model.respose;

/**
 * Created by oldman on 05.04.17.
 */
public class NewDeviceResponse extends BaseResponse{
    private int device;

    public NewDeviceResponse(int code, int device) {
        super(code);
        this.device = device;
    }
    public int getDevice() {
        return device;
    }

    public void setDevice(int device) {
        this.device = device;
    }
}
