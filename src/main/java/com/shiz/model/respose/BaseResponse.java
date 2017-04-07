package com.shiz.model.respose;

/**
 * Created by oldman on 04.04.17.
 */
public class BaseResponse {
    private int code;

    public BaseResponse(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
