package com.shiz.dao;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by oldman on 14.04.17.
 */
public class MessageEntityPK implements Serializable {
    private int id;
    private int deviceId;
    private int typeEventId;

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

    @Column(name = "type_event_id", nullable = false)
    @Id
    public int getTypeEventId() {
        return typeEventId;
    }

    public void setTypeEventId(int typeEventId) {
        this.typeEventId = typeEventId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessageEntityPK that = (MessageEntityPK) o;

        if (id != that.id) return false;
        if (deviceId != that.deviceId) return false;
        if (typeEventId != that.typeEventId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + deviceId;
        result = 31 * result + typeEventId;
        return result;
    }
}
