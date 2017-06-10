package com.shiz.model.request;


/**
 * Created by oldman on 05.04.17.
 */
public class InitRequest {
    private String imei;
    private String version_os;
    private String model;


    public InitRequest(String imei, String model, String version_os) {
        this.imei = imei;
        this.model = model;
        this.version_os = version_os;
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

    public String getVersion_os() {
        return version_os;
    }

    public void setVersion_os(String version_os) {
        this.version_os = version_os;
    }
}
