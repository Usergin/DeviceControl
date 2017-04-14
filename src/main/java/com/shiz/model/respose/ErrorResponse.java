package com.shiz.model.respose;

/**
 * Created by oldman on 05.04.17.
 */
public class ErrorResponse extends BaseResponse {
     private String  error;

    public ErrorResponse(int code, String error) {
        super(code);
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String device) {
        this.error = device;
    }
}