package com.shiz.repository.db.dao;

import com.shiz.model.data.event.BatteryEvent;

import java.util.List;

/**
 * Created by OldMan on 09.05.2017.
 */
public interface BatteryStatusDao {
    void addBatteryStatus(int deviceId, List<BatteryEvent> batteryStatusEntity) throws Exception;

    List<BatteryEvent> getBatteryEventList(int deviceId) throws Exception;
}
