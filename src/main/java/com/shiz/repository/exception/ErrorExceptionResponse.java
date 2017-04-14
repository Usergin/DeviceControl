package com.shiz.repository.exception;

/**
 * Created by oldman on 12.04.17.
 */
public class ErrorExceptionResponse extends RuntimeException {
    private String error;
    private int code;

    public ErrorExceptionResponse(int code, String error) {
        this.error = error;
        this.code = code;
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