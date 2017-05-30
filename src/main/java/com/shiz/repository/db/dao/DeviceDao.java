package com.shiz.repository.db.dao;

import com.shiz.entity.DeviceEntity;
import com.shiz.model.Device;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by oldman on 22.04.17.
 */
public interface DeviceDao {
    int addDevice(DeviceEntity deviceEntity) throws SQLException, Exception;

    List<Device> getAllDevice() throws SQLException, Exception;

    void deleteAllDevice() throws SQLException, Exception;

    Device getDeviceByDeviceId(int deviceId) throws SQLException, Exception;

    Device getDeviceIdByImei(String imei) throws SQLException, Exception;

    void deleteDeviceById(int deviceId) throws SQLException, Exception;


}
