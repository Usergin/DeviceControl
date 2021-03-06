package com.shiz.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by oldman on 19.04.17.
 */
@Entity
@Table(name = "app")
@IdClass(AppEntityPK.class)
public class AppEntity {
    private int id;
    private String name;
    private Date dateInstalled;
    private String info;
    private DeviceEntity appByDeviceId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Basic
    @Column(name = "name", nullable = false, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "date_installed", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDateInstalled() {
        return dateInstalled;
    }

    public void setDateInstalled(Date dateInstalled) {
        this.dateInstalled = dateInstalled;
    }

    @Basic
    @Column(name = "info", nullable = true, length = 255)
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AppEntity appEntity = (AppEntity) o;

        if (id != appEntity.id) return false;
//        if (deviceId != appEntity.deviceId) return false;
        if (name != null ? !name.equals(appEntity.name) : appEntity.name != null) return false;
        if (dateInstalled != null ? !dateInstalled.equals(appEntity.dateInstalled) : appEntity.dateInstalled != null)
            return false;
        if (info != null ? !info.equals(appEntity.info) : appEntity.info != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
//        result = 31 * result + deviceId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (dateInstalled != null ? dateInstalled.hashCode() : 0);
        result = 31 * result + (info != null ? info.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "device_id")
    public DeviceEntity getAppByDeviceId() {
        return appByDeviceId;
    }

    public void setAppByDeviceId(DeviceEntity appByDeviceId) {
        this.appByDeviceId = appByDeviceId;
    }
}
