package com.shiz.repository.db.dao;

import com.shiz.entity.*;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by oldman on 22.04.17.
 */
public interface DeviceDao {
    int addDevice(DeviceEntity deviceEntity) throws SQLException, Exception;

    List<DeviceEntity> getAllDevice() throws SQLException, Exception;

    boolean deleteAllDevice() throws SQLException, Exception;

    DeviceEntity getDeviceByDeviceId(int deviceId) throws SQLException, Exception;

    int getDeviceIdByImei(String imei) throws SQLException, Exception;

    void deleteDeviceById(int deviceId) throws SQLException, Exception;

    void addMessageList(int deviceId, List<MessageEntity> messageEntityList) throws SQLException, Exception;

    List<MessageEntity> getMessageEntityList(int deviceId) throws SQLException, Exception;

    void addTelephoneBookList(int deviceId, List<TelephoneBookEntity> telephoneBookEntityList) throws SQLException, Exception;

    List<TelephoneBookEntity> getTelephoneBookEntityList(int deviceId) throws SQLException, Exception;

    void addLocation(int deviceId, LocationEntity locationEntity) throws SQLException, Exception;

    List<LocationEntity> getLocationEntityList(int deviceId) throws SQLException, Exception;

    void addDeviceStatus(int deviceId, DeviceStatusEntity deviceStatusEntity) throws SQLException, Exception;

    List<DeviceStatusEntity> getDeviceStatusList(int deviceId) throws SQLException, Exception;

    void addDeviceStatus(int deviceId, NetworkStatusEntity networkStatusEntity) throws SQLException, Exception;

    List<NetworkStatusEntity> getNetworkStatusList(int deviceId) throws SQLException, Exception;

    void setSettings(int deviceId, SettingsEntity settingsEntity) throws SQLException, Exception;

    SettingsEntity getSettingsEntity(int deviceId) throws SQLException, Exception;

    void setDeviceInfo(int deviceId, InformationEntity settingsEntity) throws SQLException, Exception;

    InformationEntity getDeviceInfoEntity(int deviceId) throws SQLException, Exception;


}
