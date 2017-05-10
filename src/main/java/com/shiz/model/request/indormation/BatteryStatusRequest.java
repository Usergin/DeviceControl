package com.shiz.model.request.indormation;

import com.shiz.model.data.event.BatteryEvent;
import com.shiz.model.request.BaseRequest;

import java.util.List;

/**
 * Created by oldman on 06.04.17.
 */
public class BatteryStatusRequest extends BaseRequest {
    private List<BatteryEvent> data;

    public BatteryStatusRequest(List<BatteryEvent> data, String imei, int device) {
        super(imei, device);
        this.data = data;
    }

    public List<BatteryEvent> getData() {
        return data;
    }

    public void setData(List<BatteryEvent> data) {
        this.data = data;
    }
}