package com.shiz.model.request.indormation;

import com.shiz.model.data.event.Call;
import com.shiz.model.request.BaseRequest;

import java.util.List;

/**
 * Created by oldman on 05.04.17.
 */
public class CallRequest extends BaseRequest {
    private List<Call> data;

    public CallRequest(List<Call> data, String imei, int device) {
        super(imei, device);
        this.data = data;
    }

    public List<Call> getData() {
        return data;
    }

    public void setData(List<Call> data) {
        this.data = data;
    }
}