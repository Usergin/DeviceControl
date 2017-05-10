package com.shiz.repository.db.dao;

import com.shiz.entity.BatteryEntity;
import com.shiz.entity.LocationEntity;

import java.util.List;

/**
 * Created by OldMan on 10.05.2017.
 */
public interface LocationDao {
    void addLocation(int deviceId, List<LocationEntity> batteryStatusEntity) throws Exception;
    List<LocationEntity> getLocationEntityList(int deviceId) throws Exception;

}
