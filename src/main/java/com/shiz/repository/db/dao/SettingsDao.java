package com.shiz.repository.db.dao;

import com.shiz.model.data.Settings;

/**
 * Created by OldMan on 19.05.2017.
 */
public interface SettingsDao {

    void setSettings(int deviceId, Settings settings) throws Exception;

    void setDefaultSettings(int deviceId) throws Exception;

    Settings getSettingsByDevice(int deviceId) throws Exception;

    Settings getSettingsByControlPoint(int deviceId) throws Exception;

}
