package com.shiz.model.request;

/**
 * Created by oldman on 05.04.17.
 */
public class DeleteDeviceRequest extends BaseRequest {
    private String imei;
    private String device;
    private String mode;

    public DeleteDeviceRequest(String mode, String imei, int device) {
        super(imei, device);
        this.mode = mode;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}
