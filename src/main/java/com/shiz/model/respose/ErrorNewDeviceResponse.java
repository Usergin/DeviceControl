package com.shiz.model.respose;

/**
 * Created by oldman on 14.04.17.
 */
public class ErrorNewDeviceResponse  extends ErrorResponse{
    private int device;


    public ErrorNewDeviceResponse(int code, String error, int device) {
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

