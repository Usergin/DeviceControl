package com.shiz.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oldman on 19.04.17.
 */
@Entity
@Table(name = "Device")
@NamedQueries({
        @NamedQuery(name = DeviceEntity.NamedQuery.DEVICE_FIND_ALL, query = "from DeviceEntity"),
        @NamedQuery(name = DeviceEntity.NamedQuery.DEVICE_FIND_BY_ID, query = "from DeviceEntity where deviceId = :device_id"),
        @NamedQuery(name = DeviceEntity.NamedQuery.DEVICE_FIND_BY_IMEI, query = "from DeviceEntity where imei like :imei")})
public class DeviceEntity implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Basic
    @Column(name = "imei", nullable = false, length = 45)
    private String imei;
    @Column(name = "device_id")
    private int deviceId;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "appByDeviceId", orphanRemoval = true)
    private List<AppEntity> appByDeviceId = new ArrayList<>();
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "batteryByDeviceId", orphanRemoval = true)
    private List<BatteryStatusEntity> batteryStatusByDeviceId = new ArrayList<>();
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "settingsDeviceByDeviceId", orphanRemoval = true)
    private SettingsEntity settingsByDeviceId;

    public static class NamedQuery {
        public static final String DEVICE_FIND_ALL = "DeviceEntity.findAll";
        public static final String DEVICE_FIND_BY_ID = "DeviceEntity.findById";
        public static final String DEVICE_FIND_BY_IMEI = "DeviceEntity.findByImei";
    }

//    private Collection<CallEntity> callByDeviceId;
//    private Collection<DeviceStatusEntity> deviceStatusByDeviceId;
//    private Collection<LocationEntity> locationByDeviceId;
//    private Collection<MessageEntity> messageByDeviceId;
//    private Collection<NetworkStatusEntity> networkStatusByDeviceId;
//    private Collection<TelephoneBookEntity> telephoneBookByDeviceId;
//    private InformationEntity infoByDeviceId;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

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

    public List<AppEntity> getAppByDeviceId() {
        return appByDeviceId;
    }

    public void setAppByDeviceId(List<AppEntity> appByDeviceId) {
        this.appByDeviceId = appByDeviceId;
    }

    public void addApp(AppEntity app) {
        if (!getAppByDeviceId().contains(app)) {
            System.out.print(app.getName());
            this.appByDeviceId.add(app);
        }
    }

    public void addAppsList(List appByDeviceIdList) {
        if (appByDeviceIdList != null)
            this.appByDeviceId.addAll(appByDeviceIdList);
    }

    public List<BatteryStatusEntity> getBatteryStatusByDeviceId() {
        return batteryStatusByDeviceId;
    }

    public void setBatteryStatusByDeviceId(List<BatteryStatusEntity> batteryStatusByDeviceId) {
        this.batteryStatusByDeviceId = batteryStatusByDeviceId;
    }

    public void addBatteryStatusByDeviceId(BatteryStatusEntity batteryStatusByDeviceId) {
        this.batteryStatusByDeviceId.add(batteryStatusByDeviceId);
    }

    public SettingsEntity getSettingsByDeviceId() {
        return settingsByDeviceId;
    }

    public void setSettingsByDeviceId(SettingsEntity settingsByDeviceId) {
        this.settingsByDeviceId = settingsByDeviceId;
    }

    //
//    @OneToMany(mappedBy = "callByDeviceId")
//    public Collection<CallEntity> getCallByDeviceId() {
//        return callByDeviceId;
//    }
//
//    public void setCallByDeviceId(Collection<CallEntity> callByDeviceId) {
//        this.callByDeviceId = callByDeviceId;
//    }
//
//    @OneToMany(mappedBy = "deviceStatusByDeviceId")
//    public Collection<DeviceStatusEntity> getDeviceStatusByDeviceId() {
//        return deviceStatusByDeviceId;
//    }
//
//    public void setDeviceStatusByDeviceId(Collection<DeviceStatusEntity> deviceStatusByDeviceId) {
//        this.deviceStatusByDeviceId = deviceStatusByDeviceId;
//    }
//
//    @OneToMany(mappedBy = "locationByDeviceId")
//    public Collection<LocationEntity> getLocationByDeviceId() {
//        return locationByDeviceId;
//    }
//
//    public void setLocationByDeviceId(Collection<LocationEntity> locationByDeviceId) {
//        this.locationByDeviceId = locationByDeviceId;
//    }
//
//    @OneToMany(mappedBy = "msgByDeviceId")
//    public Collection<MessageEntity> getMessageByDeviceId() {
//        return messageByDeviceId;
//    }
//
//    public void setMessageByDeviceId(Collection<MessageEntity> messageByDeviceId) {
//        this.messageByDeviceId = messageByDeviceId;
//    }
//
//    @OneToMany(mappedBy = "networkStatusByDeviceId")
//    public Collection<NetworkStatusEntity> getNetworkStatusByDeviceId() {
//        return networkStatusByDeviceId;
//    }
//
//    public void setNetworkStatusByDeviceId(Collection<NetworkStatusEntity> networkStatusByDeviceId) {
//        this.networkStatusByDeviceId = networkStatusByDeviceId;
//    }
//
//    @OneToMany(mappedBy = "telBookByDeviceId")
//    public Collection<TelephoneBookEntity> getTelephoneBookByDeviceId() {
//        return telephoneBookByDeviceId;
//    }
//
//    public void setTelephoneBookByDeviceId(Collection<TelephoneBookEntity> telephoneBookByDeviceId) {
//        this.telephoneBookByDeviceId = telephoneBookByDeviceId;
//    }
//
//    @OneToOne(mappedBy = "deviceByDeviceId")
//    public InformationEntity getInfoByDeviceId() {
//        return infoByDeviceId;
//    }
//
//    public void setInfoByDeviceId(InformationEntity infoByDeviceId) {
//        this.infoByDeviceId = infoByDeviceId;
//    }
//
//    @OneToOne(mappedBy = "deviceByDeviceId")
//    public SettingsEntity getSettingsByDeviceId() {
//        return settingsByDeviceId;
//    }
//
//    public void setSettingsByDeviceId(SettingsEntity settingsByDeviceId) {
//        this.settingsByDeviceId = settingsByDeviceId;
//    }
//    private Set<AppEntity> oneToMany;
//
//    @OneToMany
//    public Set<AppEntity> getOneToMany() {
//        return oneToMany;
//    }
//
//    public void setOneToMany(Set<AppEntity> oneToMany) {
//        this.oneToMany = oneToMany;
//    }
}
