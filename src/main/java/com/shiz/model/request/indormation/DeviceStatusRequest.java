package com.shiz.model.request.indormation;

import com.shiz.model.data.event.Call;
import com.shiz.model.data.event.DeviceEvent;
import com.shiz.model.request.BaseRequest;

import java.util.List;

/**
 * Created by oldman on 06.04.17.
 */
public class DeviceStatusRequest extends BaseRequest {
    private List<DeviceEvent> data;

    public DeviceStatusRequest( List<DeviceEvent> data, String imei, int device) {
        super(imei, device);
        this.data = data;
    }

    public   List<DeviceEvent> getData() {
        return data;
    }

    public void setData(  List<DeviceEvent> data) {
        this.data = data;
    }
}