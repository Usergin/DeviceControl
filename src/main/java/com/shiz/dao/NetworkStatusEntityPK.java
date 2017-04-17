package com.shiz.dao;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by oldman on 17.04.17.
 */
public class NetworkStatusEntityPK implements Serializable {
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

        NetworkStatusEntityPK that = (NetworkStatusEntityPK) o;

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
