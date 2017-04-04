package com.shiz.model;

/**
 * Created by oldman on 04.04.17.
 */
public class Response {
    private String response;
    private int imei;

    public Response(int imei, String response) {
        this.imei = imei;
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public int getImei() {
        return imei;
    }

    public void setImei(int imei) {
        this.imei = imei;
    }
}
