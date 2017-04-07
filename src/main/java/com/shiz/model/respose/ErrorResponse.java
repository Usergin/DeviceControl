package com.shiz.model.respose;

/**
 * Created by oldman on 05.04.17.
 */
public class ErrorResponse extends RuntimeException  {
    private int code;
    private String  error;

    public ErrorResponse(int code, String error) {
        this.code = code;
        this.error = error;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDevice() {
        return error;
    }

    public void setDevice(String device) {
        this.error = device;
    }
}