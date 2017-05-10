package com.shiz.model.respose;

import com.shiz.model.data.event.BaseEvent;

/**
 * Created by oldman on 05.04.17.
 */
public class InformationResponse extends BaseResponse {
    private Object info;

    public InformationResponse(int code, Object object) {
        super(code);
        this.info = object;
    }

    public Object getInfo() {
        return info;
    }

    public void setInfo(Object info) {
        this.info = info;
    }
}
