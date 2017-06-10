package com.shiz.model.respose.error;

/**
 * Created by oldman on 14.04.17.
 */
public class ErrorDeviceIdResponse extends ErrorResponse {
    private int device;


    public ErrorDeviceIdResponse(int code, int error, int device) {
        super(code, error);
        this.device = device;
    }

    public int getDevice() {
        return device;
    }

    public void setDevice(int device) {
        this.device = device;
    }

}

