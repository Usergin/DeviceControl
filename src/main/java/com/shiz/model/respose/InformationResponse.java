package com.shiz.model.respose;

/**
 * Created by oldman on 05.04.17.
 */
public class InformationResponse extends BaseResponse {
    private Object data;

    public InformationResponse(int code, Object object) {
        super(code);
        this.data = object;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
