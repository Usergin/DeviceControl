package com.shiz.model.request;


/**
 * Created by oldman on 05.04.17.
 */
public class InitialDeviceRequest {
    private String imei;
    private String model;

    public InitialDeviceRequest(String imei, String model) {
        this.imei = imei;
        this.model = model;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
