package com.shiz.model.request.indormation;

import com.shiz.model.data.DeviceInfo;
import com.shiz.model.request.BaseRequest;

/**
 * Created by oldman on 10.05.17.
 */
public class DeviceInformationRequest extends BaseRequest {
    private DeviceInfo data;

    public DeviceInformationRequest(DeviceInfo deviceInfo, String imei, int device) {
        super(imei, device);
        this.data = deviceInfo;
    }

    public DeviceInfo getData() {
        return data;
    }

    public void setData(DeviceInfo data) {
        this.data = data;
    }
}