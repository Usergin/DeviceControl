package com.shiz.repository.db.dao;

import com.shiz.entity.AppEntity;
import com.shiz.entity.BatteryStatusEntity;
import com.shiz.entity.DeviceEntity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oldman on 22.04.17.
 */
public interface DeviceDao {
    public int addDevice(DeviceEntity deviceEntity) throws SQLException, Exception;

    public List getAllDevice() throws SQLException, Exception;

    public boolean deleteAllDevice() throws SQLException, Exception;

    public DeviceEntity getDeviceByDeviceId(int deviceId) throws SQLException, Exception;

    public int getDeviceIdByImei(String imei) throws SQLException, Exception;

    public void deleteDeviceById(int deviceId) throws SQLException, Exception;

    public int addAppList(int deviceId, List<AppEntity> appEntities) throws SQLException, Exception;

    public int addBatteryStatus(int deviceId, BatteryStatusEntity batteryStatusEntity) throws SQLException, Exception;
}
