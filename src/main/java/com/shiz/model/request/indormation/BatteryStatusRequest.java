package com.shiz.model.request.indormation;

import com.shiz.model.data.event.Call;
import com.shiz.model.data.event.ChargingEvent;
import com.shiz.model.request.BaseRequest;

import java.util.List;

/**
 * Created by oldman on 06.04.17.
 */
public class BatteryStatusRequest extends BaseRequest {
    private ChargingEvent data;

    public BatteryStatusRequest(ChargingEvent data, String imei, String device) {
        super(imei, device);
        this.data = data;
    }

    public  ChargingEvent getData() {
        return data;
    }

    public void setData(ChargingEvent data) {
        this.data = data;
    }
}