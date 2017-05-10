package com.shiz.repository.db.dao;

import com.shiz.entity.CallEntity;
import com.shiz.entity.DeviceStatusEntity;

import java.util.List;

/**
 * Created by OldMan on 10.05.2017.
 */
public interface DeviceStatusDao {
    void addDeviceStatus(int deviceId, DeviceStatusEntity deviceStatusEntity) throws Exception;
    List<DeviceStatusEntity> getDeviceStatusEntityList(int deviceId) throws Exception;
}
