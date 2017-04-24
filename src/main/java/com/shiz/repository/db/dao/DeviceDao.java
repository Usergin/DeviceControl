package com.shiz.repository.db.dao;

import com.shiz.entity.DeviceEntity;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by oldman on 22.04.17.
 */
public interface DeviceDao {
    public int addDevice(DeviceEntity deviceEntity) throws SQLException, Exception;

    public void addDevicesList(List<DeviceEntity> deviceEntity) throws SQLException, Exception;

    public List getAllDevice() throws SQLException, Exception;

    public void deleteAllDevice(List<DeviceEntity> deviceEntityCollection) throws SQLException, Exception;

    public DeviceEntity getDeviceByDeviceId(int deviceId) throws SQLException, Exception;

    public int getDeviceIdByImei(String imei) throws SQLException, Exception;
}
