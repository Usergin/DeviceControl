package com.shiz.dao;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by oldman on 14.04.17.
 */
@Entity
@Table(name = "Message", schema = "mydb", catalog = "")
@IdClass(MessageEntityPK.class)
public class MessageEntity {
    private int id;
    private int deviceId;
    private String number;
    private String data;
    private Timestamp date;
    private int typeEventId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    @Column(name = "device_id", nullable = false)
    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    @Basic
    @Column(name = "number", nullable = false, length = 45)
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Basic
    @Column(name = "data", nullable = true, length = -1)
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Basic
    @Column(name = "date", nullable = false)
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Id
    @Column(name = "type_event_id", nullable = false)
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

        MessageEntity that = (MessageEntity) o;

        if (id != that.id) return false;
        if (deviceId != that.deviceId) return false;
        if (typeEventId != that.typeEventId) return false;
        if (number != null ? !number.equals(that.number) : that.number != null) return false;
        if (data != null ? !data.equals(that.data) : that.data != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + deviceId;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (data != null ? data.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + typeEventId;
        return result;
    }
}
