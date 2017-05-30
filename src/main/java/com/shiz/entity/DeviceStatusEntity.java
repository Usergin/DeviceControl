package com.shiz.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by oldman on 19.04.17.
 */
@Entity
@Table(name = "device_status", schema = "mydb")
@IdClass(DeviceStatusEntityPK.class)
public class DeviceStatusEntity {
    private int id;
    private Timestamp date;
    private String status;
    private DeviceEntity deviceStatusByDeviceId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "date", nullable = false)
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Basic
    @Column(name = "status", nullable = false, length = 45)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @ManyToOne
    @JoinColumn(name = "device_id")
    public DeviceEntity getDeviceStatusByDeviceId() {
        return deviceStatusByDeviceId;
    }

    public void setDeviceStatusByDeviceId(DeviceEntity deviceStatusByDeviceId) {
        this.deviceStatusByDeviceId = deviceStatusByDeviceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeviceStatusEntity that = (DeviceStatusEntity) o;

        if (id != that.id) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
