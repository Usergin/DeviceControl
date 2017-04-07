package com.shiz.model.request.indormation;

import com.shiz.model.data.event.Call;
import com.shiz.model.data.event.DeviceEvent;
import com.shiz.model.request.BaseRequest;

import java.util.List;

/**
 * Created by oldman on 06.04.17.
 */
public class DeviceStatusRequest extends BaseRequest {
    private DeviceEvent data;

    public DeviceStatusRequest(DeviceEvent data, String imei, String device) {
        super(imei, device);
        this.data = data;
    }

    public  DeviceEvent getData() {
        return data;
    }

    public void setData( DeviceEvent data) {
        this.data = data;
    }
}