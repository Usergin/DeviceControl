package com.shiz.repository.db.dao;

import com.shiz.model.data.DeviceInfo;

/**
 * Created by oldman on 10.05.17.
 */
public interface InformationDao {
    void setDeviceInformation(int deviceId, DeviceInfo deviceInfo) throws Exception;

    DeviceInfo getDeviceInfo(int deviceId) throws Exception;
}
