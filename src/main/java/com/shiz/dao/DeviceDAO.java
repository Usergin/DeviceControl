package com.shiz.dao;

import java.util.List;

/**
 * Created by oldman on 12.04.17.
 */
public interface DeviceDAO {

    public void addDevice(DeviceEntity p);
    public void updatePerson(DeviceEntity p);
    public List<DeviceEntity> getListDevice();
    public DeviceEntity getDeviceById(int id);
    public void removeDevice(int id);
}
