package com.shiz.model.request;

/**
 * Created by oldman on 05.04.17.
 */
public class BaseRequest {
    private String imei;
    private int device;

    public BaseRequest(String imei, int device) {
        this.imei = imei;
        this.device = device;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public int getDevice() {
        return device;
    }

    public void setDevice(int device) {
        this.device = device;
    }


}
