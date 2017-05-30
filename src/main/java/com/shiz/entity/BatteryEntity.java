package com.shiz.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by oldman on 19.04.17.
 */
@Entity
@Table(name = "battery")
@IdClass(BatteryEntityPK.class)
public class BatteryEntity {
    private int id;
    private String level;
    private Timestamp date;
    private String status;
    private String batteryStatus;
    private String typeCharging;
    private DeviceEntity batteryByDeviceId;

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
    @Column(name = "level", nullable = false, length = 45)
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
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
    @Column(name = "status", nullable = true, length = 45)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "battery_status", nullable = true, length = 45)
    public String getBatteryStatus() {
        return batteryStatus;
    }

    public void setBatteryStatus(String batteryStatus) {
        this.batteryStatus = batteryStatus;
    }

    @Basic
    @Column(name = "type_charging", nullable = true, length = 45)
    public String getTypeCharging() {
        return typeCharging;
    }

    public void setTypeCharging(String typeCharging) {
        this.typeCharging = typeCharging;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BatteryEntity that = (BatteryEntity) o;

        if (id != that.id) return false;
        if (level != null ? !level.equals(that.level) : that.level != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (batteryStatus != null ? !batteryStatus.equals(that.batteryStatus) : that.batteryStatus != null)
            return false;
        if (typeCharging != null ? !typeCharging.equals(that.typeCharging) : that.typeCharging != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (level != null ? level.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (batteryStatus != null ? batteryStatus.hashCode() : 0);
        result = 31 * result + (typeCharging != null ? typeCharging.hashCode() : 0);
        return result;
    }
    @ManyToOne
    @JoinColumn(name = "device_id")
    public DeviceEntity getBatteryByDeviceId() {
        return batteryByDeviceId;
    }

    public void setBatteryByDeviceId(DeviceEntity batteryByDeviceId) {
        this.batteryByDeviceId = batteryByDeviceId;
    }
}
