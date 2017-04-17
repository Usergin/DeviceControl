package com.shiz.dao;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.FetchType.LAZY;

/**
 * Created by oldman on 17.04.17.
 */
@Entity
@Table(name = "Device", schema = "mydb")
public class DeviceEntity {
    private int id;
    private String imei;
    private int deviceId;
    private Set<AppEntity> appDeviceById = new HashSet<AppEntity>() {
    };
//    private Collection<BatteryStatusEntity> batDeviceById;
//    private Collection<CallEntity> callDeviceById;
//    private Collection<InformationEntity> infoDeviceById;
//    private Collection<LocationEntity> locDeviceById;
//    private Collection<MessageEntity> msgDeviceById;
//    private Collection<NetworkStatusEntity> networkDeviceById;
    private SettingsEntity settingsDeviceById;
//    private Collection<TelephoneBookEntity> telBookDeviceById;

    @Basic
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany(mappedBy = "deviceById", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<AppEntity> getAppDeviceById() {
        return appDeviceById;
    }

    public void setAppDeviceById(Set<AppEntity> appDeviceById) {
        this.appDeviceById = appDeviceById;
    }

    public void addCAppDeviceById(AppEntity appDeviceById) {
        appDeviceById.setDeviceById(this);
        getAppDeviceById().add(appDeviceById);
    }

    public void removeAppDeviceById(AppEntity appDeviceById) {
        getAppDeviceById().remove(appDeviceById);
    }
//
//    @OneToMany(mappedBy = "deviceById")
//    public Collection<BatteryStatusEntity> getBatDeviceById() {
//        return batDeviceById;
//    }
//
//    public void setBatDeviceById(Collection<BatteryStatusEntity> batDeviceById) {
//        this.batDeviceById = batDeviceById;
//    }
//
//    @OneToMany(mappedBy = "deviceById")
//    public Collection<CallEntity> getCallDeviceById() {
//        return callDeviceById;
//    }
//
//    public void setCallDeviceById(Collection<CallEntity> callDeviceById) {
//        this.callDeviceById = callDeviceById;
//    }
//
//    @OneToMany(mappedBy = "deviceById")
//    public Collection<InformationEntity> getInfoDeviceById() {
//        return infoDeviceById;
//    }
//
//    public void setInfoDeviceById(Collection<InformationEntity> infoDeviceById) {
//        this.infoDeviceById = infoDeviceById;
//    }
//
//    @OneToMany(mappedBy = "deviceById")
//    public Collection<LocationEntity> getLocDeviceById() {
//        return locDeviceById;
//    }
//
//    public void setLocDeviceById(Collection<LocationEntity> locDeviceById) {
//        this.locDeviceById = locDeviceById;
//    }
//
//    @OneToMany(mappedBy = "deviceById")
//    public Collection<MessageEntity> getMsgDeviceById() {
//        return msgDeviceById;
//    }
//
//    public void setMsgDeviceById(Collection<MessageEntity> msgDeviceById) {
//        this.msgDeviceById = msgDeviceById;
//    }
//
//    @OneToMany(mappedBy = "deviceById")
//    public Collection<NetworkStatusEntity> getNetworkDeviceById() {
//        return networkDeviceById;
//    }
//
//    public void setNetworkDeviceById(Collection<NetworkStatusEntity> networkDeviceById) {
//        this.networkDeviceById = networkDeviceById;
//    }
//
    @OneToOne(mappedBy = "deviceById", cascade = CascadeType.ALL, orphanRemoval = true)
    public SettingsEntity getSettingsDeviceById() {
        return settingsDeviceById;
    }

    public void setSettingsDeviceById(SettingsEntity settingsDeviceById) {
        this.settingsDeviceById = settingsDeviceById;
    }

//    @OneToMany(mappedBy = "deviceById")
//    public Collection<TelephoneBookEntity> getTelBookDeviceById() {
//        return telBookDeviceById;
//    }
//
//    public void setTelBookDeviceById(Collection<TelephoneBookEntity> telBookDeviceById) {
//        this.telBookDeviceById = telBookDeviceById;
//    }
}
