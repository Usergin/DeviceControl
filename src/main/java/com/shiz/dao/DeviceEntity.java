package com.shiz.dao;

import javax.persistence.*;

/**
 * Created by oldman on 14.04.17.
 */
@Entity
@Table(name = "Device", schema = "mydb", catalog = "")
@IdClass(DeviceEntityPK.class)
public class DeviceEntity {
    private int id;
    private String imei;
    private int deviceId;
    private AppEntity appByDeviceId;
    private BatteryStatusEntity batteryStatusByDeviceId;
    private CallEntity callByDeviceId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "imei", nullable = false, length = 45)
    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    @Id
    @Column(name = "device_id", nullable = false)
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

        DeviceEntity that = (DeviceEntity) o;

        if (id != that.id) return false;
        if (deviceId != that.deviceId) return false;
        if (imei != null ? !imei.equals(that.imei) : that.imei != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (imei != null ? imei.hashCode() : 0);
        result = 31 * result + deviceId;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "device_id", referencedColumnName = "device_id", nullable = false)
    public AppEntity getAppByDeviceId() {
        return appByDeviceId;
    }

    public void setAppByDeviceId(AppEntity appByDeviceId) {
        this.appByDeviceId = appByDeviceId;
    }

    @ManyToOne
    @JoinColumn(name = "device_id", referencedColumnName = "device_id", nullable = false)
    public BatteryStatusEntity getBatteryStatusByDeviceId() {
        return batteryStatusByDeviceId;
    }

    public void setBatteryStatusByDeviceId(BatteryStatusEntity batteryStatusByDeviceId) {
        this.batteryStatusByDeviceId = batteryStatusByDeviceId;
    }

    @ManyToOne
    @JoinColumn(name = "device_id", referencedColumnName = "device_id", nullable = false)
    public CallEntity getCallByDeviceId() {
        return callByDeviceId;
    }

    public void setCallByDeviceId(CallEntity callByDeviceId) {
        this.callByDeviceId = callByDeviceId;
    }
}
