package com.shiz.model.request.indormation;

import com.shiz.model.data.event.NetworkEvent;
import com.shiz.model.data.event.ServiceEvent;
import com.shiz.model.request.BaseRequest;

import java.util.List;

/**
 * Created by OldMan on 19.05.2017.
 */
public class ServiceEventRequest extends BaseRequest {
    private List<ServiceEvent> data;

    public ServiceEventRequest(List<ServiceEvent> data, String imei, int device) {
        super(imei, device);
        this.data = data;
    }

    public List<ServiceEvent> getData() {
        return data;
    }

    public void setData(List<ServiceEvent> data) {
        this.data = data;
    }
}