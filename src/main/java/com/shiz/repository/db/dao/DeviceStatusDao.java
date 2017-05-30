package com.shiz.repository.db.dao;

import com.shiz.entity.CallEntity;
import com.shiz.entity.DeviceStatusEntity;
import com.shiz.model.data.event.DeviceEvent;

import java.util.List;

/**
 * Created by OldMan on 10.05.2017.
 */
public interface DeviceStatusDao {
    void addDeviceStatus(int deviceId, List<DeviceEvent> deviceEvent) throws Exception;
    List<DeviceEvent> getDeviceStatusList(int deviceId) throws Exception;
}
