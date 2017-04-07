package com.shiz.db;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by oldman on 07.04.17.
 */
public class DeviceStatusEntityPK implements Serializable {
    private int id;
    private int deviceId;

    @Column(name = "id", nullable = false)
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "device_id", nullable = false)
    @Id
    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeviceStatusEntityPK that = (DeviceStatusEntityPK) o;

        if (id != that.id) return false;
        if (deviceId != that.deviceId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + deviceId;
        return result;
    }
}
