package com.shiz.model.respose.error;

import com.shiz.model.respose.BaseResponse;

/**
 * Created by oldman on 05.04.17.
 */
public class ErrorResponse extends BaseResponse {
     private int  error;

    public ErrorResponse(int code, int error) {
        super(code);
        this.error = error;
    }

    public int getError() {
        return error;
    }

    public void setError(int device) {
        this.error = device;
    }
}