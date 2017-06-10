package com.shiz.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oldman on 19.04.17.
 */
@Entity
@Table(name = "device", schema = "mydb")
@NamedQueries({
        @NamedQuery(name = DeviceEntity.NamedQuery.DEVICE_FIND_ALL, query = "from DeviceEntity"),
        @NamedQuery(name = DeviceEntity.NamedQuery.DEVICE_FIND_BY_ID, query = "from DeviceEntity where deviceId = :device_id"),
        @NamedQuery(name = DeviceEntity.NamedQuery.DEVICE_FIND_BY_IMEI, query = "from DeviceEntity where imei like :imei")})
@IdClass(DeviceEntityPK.class)
public class DeviceEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Basic
    @Column(name = "imei", nullable = false, length = 45)
    private String imei;
    @Column(name = "device_id")
    private int deviceId;
    @Basic
    @Column(name = "model", nullable = true, length = 45)
    private String model;
    @Basic
    @Column(name = "longitude", nullable = true, precision = 0)
    private Double longitude;
    @Basic
    @Column(name = "latitude", nullable = true, precision = 0)
    private Double latitude;
    @Basic
    @Column(name = "version_os", nullable = true, length = 45)
    private String versionOs;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "appByDeviceId", orphanRemoval = true)
    private List<AppEntity> appByDeviceId = new ArrayList<>();
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "callByDeviceId", orphanRemoval = true)
    private List<CallEntity> callByDeviceId = new ArrayList<>();
    //    cascade = {CascadeType.DETACH,
//            CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE},
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "contactBookByDeviceId", orphanRemoval = true)
    private List<ContactBookEntity> contactBookByDeviceId = new ArrayList<>();
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "messageByDeviceId", orphanRemoval = true)
    private List<MessageEntity> messageByDeviceId = new ArrayList<>();
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "batteryByDeviceId", orphanRemoval = true)
    private List<BatteryEntity> batteryStatusByDeviceId = new ArrayList<>();
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "locationByDeviceId", orphanRemoval = true)
    private List<LocationEntity> locationByDeviceId = new ArrayList<>();
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "deviceStatusByDeviceId", orphanRemoval = true)
    private List<DeviceStatusEntity> deviceStatusByDeviceId = new ArrayList<>();
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "networkStatusByDeviceId", orphanRemoval = true)
    private List<NetworkStatusEntity> networkStatusByDeviceId = new ArrayList<>();
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "serviceEventByDeviceId", orphanRemoval = true)
    private List<ServiceEventEntity> serviceEventByDeviceId = new ArrayList<>();
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "settingsDeviceByDeviceId", orphanRemoval = true)
    private SettingsEntity settingsByDeviceId;
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "deviceInfoByDeviceId", orphanRemoval = true)
    private InformationEntity deviceInfoByDeviceId;


    public DeviceEntity() {
    }

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

    // application
    public List<AppEntity> getAppByDeviceId() {
        return appByDeviceId;
    }

    public void setAppByDeviceId(List<AppEntity> appList) {
        appList.stream().filter(appEntity -> !getAppByDeviceId().contains(appEntity))
                .forEach(this::addAppByDeviceId);
    }

    public void addAppByDeviceId(AppEntity app) {
        System.out.print(app.getName());
        this.appByDeviceId.add(app);
    }

    //   call
    public List<CallEntity> getCallByDeviceId() {
        return callByDeviceId;
    }

    public void setCallByDeviceId(List<CallEntity> callList) {
        callList.stream().filter(callEntity -> !getCallByDeviceId()
                .contains(callEntity))
                .forEachOrdered(this::addCallByDeviceId);
    }

    public void addCallByDeviceId(CallEntity callEntity) {
        this.callByDeviceId.add(callEntity);
    }

    public void removeCall(CallEntity callEntity) {
        this.callByDeviceId.remove(callEntity);
    }

    public boolean containCallByDeviceId(CallEntity callEntity) {
        return this.callByDeviceId.contains(callEntity);
    }

    // message
    public List<MessageEntity> getMessageByDeviceId() {
        return messageByDeviceId;
    }

    public void setMessageByDeviceId(List<MessageEntity> messageList) {
        messageList.stream().filter(messageEntity -> !this.getMessageByDeviceId()
                .contains(messageEntity))
                .forEachOrdered(this::addMessageByDeviceId);
    }

    public void addMessageByDeviceId(MessageEntity messageEntity) {
        this.messageByDeviceId.add(messageEntity);
    }

    //tellbook
    public List<ContactBookEntity> getContactBookByDeviceId() {
        return contactBookByDeviceId;
    }

    public void setContactBookByDeviceId(List<ContactBookEntity> contactBook) {
        contactBook.stream().filter(telephoneBookEntity -> !getContactBookByDeviceId()
                .contains(telephoneBookEntity))
                .forEachOrdered(telephoneBookEntity -> this.contactBookByDeviceId.add(telephoneBookEntity));
    }

    public void addContactByDeviceId(ContactBookEntity contactBookEntity) {
        this.contactBookByDeviceId.add(contactBookEntity);
    }

    public List<BatteryEntity> getBatteryStatusByDeviceId() {
        return batteryStatusByDeviceId;
    }

    public void addBatteryStatusByDeviceId(BatteryEntity batteryStatusByDeviceId) {
        this.batteryStatusByDeviceId.add(batteryStatusByDeviceId);
    }

    public List<DeviceStatusEntity> getDeviceStatusByDeviceId() {
        return deviceStatusByDeviceId;
    }

    public void addDeviceStatusByDeviceId(DeviceStatusEntity deviceStatusEntity) {
        this.deviceStatusByDeviceId.add(deviceStatusEntity);
    }

    public List<LocationEntity> getLocationByDeviceId() {
        return locationByDeviceId;
    }

    public void setLocationByDeviceId(List<LocationEntity> locationByDeviceId) {
        this.locationByDeviceId = locationByDeviceId;
    }

    public void addLocationByDeviceId(LocationEntity locationEntity) {
        this.locationByDeviceId.add(locationEntity);
    }

    public List<NetworkStatusEntity> getNetworkStatusByDeviceId() {
        return networkStatusByDeviceId;
    }

    public void setNetworkStatusByDeviceId(List<NetworkStatusEntity> networkStatusByDeviceId) {
        this.networkStatusByDeviceId = networkStatusByDeviceId;
    }

    public void addServiceEventByDeviceId(ServiceEventEntity serviceEventEntity) {
        this.serviceEventByDeviceId.add(serviceEventEntity);
    }

    public List<ServiceEventEntity> getServiceEventByDeviceId() {
        return serviceEventByDeviceId;
    }

    public void setServiceEventByDeviceId(List<ServiceEventEntity> serviceEventByDeviceId) {
        this.serviceEventByDeviceId = serviceEventByDeviceId;
    }

    public void addNetworkStatusByDeviceId(NetworkStatusEntity networkStatusEntity) {
        this.networkStatusByDeviceId.add(networkStatusEntity);
    }

    public SettingsEntity getSettingsByDeviceId() {
        return settingsByDeviceId;
    }

    public void setSettingsByDeviceId(SettingsEntity settingsByDeviceId) {
        this.settingsByDeviceId = settingsByDeviceId;
    }

    public InformationEntity getInfoByDeviceId() {
        return deviceInfoByDeviceId;
    }

    public void setInfoByDeviceId(InformationEntity infoByDeviceId) {
        this.deviceInfoByDeviceId = infoByDeviceId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

     public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

     public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

     public String getVersionOs() {
        return versionOs;
    }

    public void setVersionOs(String versionOs) {
        this.versionOs = versionOs;
    }

    public static class NamedQuery {
        public static final String DEVICE_FIND_ALL = "DeviceEntity.findAll";
        public static final String DEVICE_FIND_BY_ID = "DeviceEntity.findById";
        public static final String DEVICE_FIND_BY_IMEI = "DeviceEntity.findByImei";
    }
}
