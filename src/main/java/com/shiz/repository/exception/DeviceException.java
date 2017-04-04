package com.shiz.repository.exception;

/**
 * Created by oldman on 04.04.17.
 */
public class DeviceException extends RuntimeException {

    private final int deviceId;

    public DeviceException(int deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public String getMessage() {
        return "Device with id = " + deviceId + " not found";
    }
}
