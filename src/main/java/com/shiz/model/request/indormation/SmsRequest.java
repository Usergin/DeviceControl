package com.shiz.model.request.indormation;

import com.shiz.model.data.event.SMS;
import com.shiz.model.request.BaseRequest;

import java.util.List;

/**
 * Created by oldman on 06.04.17.
 */
public class SmsRequest extends BaseRequest {
    private List<SMS> data;

    public SmsRequest( List<SMS> data, String imei, int device) {
        super(imei, device);
        this.data = data;
    }

    public  List<SMS> getData() {
        return data;
    }

    public void setData( List<SMS> data) {
        this.data = data;
    }
}