package com.shiz.repository.db.dao;

import com.shiz.entity.AppEntity;
import com.shiz.entity.BatteryStatusEntity;
import com.shiz.entity.DeviceEntity;
import com.shiz.entity.SettingsEntity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oldman on 22.04.17.
 */
public interface DeviceDao {
    int addDevice(DeviceEntity deviceEntity) throws SQLException, Exception;

    List getAllDevice() throws SQLException, Exception;

    boolean deleteAllDevice() throws SQLException, Exception;

    DeviceEntity getDeviceByDeviceId(int deviceId) throws SQLException, Exception;

    int getDeviceIdByImei(String imei) throws SQLException, Exception;

    void deleteDeviceById(int deviceId) throws SQLException, Exception;

    int addAppList(int deviceId, List<AppEntity> appEntities) throws SQLException, Exception;

    int addBatteryStatus(int deviceId, BatteryStatusEntity batteryStatusEntity) throws SQLException, Exception;

    int setSettings(int deviceId, SettingsEntity settingsEntity) throws SQLException, Exception;

    SettingsEntity getSettings(int deviceId) throws SQLException, Exception;


}
