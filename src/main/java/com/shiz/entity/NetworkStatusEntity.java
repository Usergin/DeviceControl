package com.shiz.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by oldman on 19.04.17.
 */
@Entity
@Table(name = "NetworkStatus", schema = "mydb", catalog = "")
@IdClass(NetworkStatusEntityPK.class)
public class NetworkStatusEntity {
    private int id;
    private String state;
    private String ip;
    private Timestamp date;
    private DeviceEntity networkStatusByDeviceId;
    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "state", nullable = false, length = 45)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "ip", nullable = true, length = 45)
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Basic
    @Column(name = "date", nullable = false)
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @ManyToOne
    @JoinColumn(name = "device_id")
    public DeviceEntity getNetworkStatusByDeviceId() {
        return networkStatusByDeviceId;
    }

    public void setNetworkStatusByDeviceId(DeviceEntity networkStatusByDeviceId) {
        this.networkStatusByDeviceId = networkStatusByDeviceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NetworkStatusEntity that = (NetworkStatusEntity) o;

        if (id != that.id) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (ip != null ? !ip.equals(that.ip) : that.ip != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (ip != null ? ip.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
