package com.shiz.repository.db.dao;

import com.shiz.entity.BatteryEntity;

import java.util.List;

/**
 * Created by OldMan on 09.05.2017.
 */
public interface BatteryStatusDao {
    void addBatteryStatus(int deviceId,   List<BatteryEntity> batteryStatusEntity) throws Exception;
    List<BatteryEntity> getBatteryEntityList(int deviceId) throws Exception;
}
