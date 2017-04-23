package com.shiz.repository.db.dao;

import com.shiz.entity.DeviceEntity;

import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by oldman on 22.04.17.
 */
public interface DeviceDao {
    public DeviceEntity addDevice(DeviceEntity deviceEntity) throws SQLException, Exception;
    public void addDevicesList(Collection<DeviceEntity> deviceEntity) throws SQLException, Exception;
    public Collection<DeviceEntity> getAllDevice() throws SQLException, Exception;
    public void deleteAllDevice(Collection<DeviceEntity> deviceEntityCollection ) throws SQLException, Exception;
    public DeviceEntity getDeviceByDeviceId(int deviceId) throws SQLException, Exception;
    public int getDeviceIdByImei(String imei) throws SQLException, Exception;
}
