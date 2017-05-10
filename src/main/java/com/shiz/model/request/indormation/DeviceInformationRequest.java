package com.shiz.model.request.indormation;

import com.shiz.model.data.DeviceInfo;
import com.shiz.model.request.BaseRequest;

/**
 * Created by oldman on 10.05.17.
 */
public class DeviceInformationRequest extends BaseRequest {
    private DeviceInfo deviceInfo;

    public DeviceInformationRequest(DeviceInfo deviceInfo, String imei, int device) {
        super(imei, device);
        this.deviceInfo = deviceInfo;
    }

    public DeviceInfo getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(DeviceInfo deviceInfo) {
        this.deviceInfo = deviceInfo;
    }
}