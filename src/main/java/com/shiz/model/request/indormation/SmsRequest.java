package com.shiz.model.request.indormation;

import com.shiz.model.data.event.Message;
import com.shiz.model.request.BaseRequest;

import java.util.List;

/**
 * Created by oldman on 06.04.17.
 */
public class SmsRequest extends BaseRequest {
    private List<Message> data;

    public SmsRequest(List<Message> data, String imei, int device) {
        super(imei, device);
        this.data = data;
    }

    public List<Message> getData() {
        return data;
    }

    public void setData(List<Message> data) {
        this.data = data;
    }
}