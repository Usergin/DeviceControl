package com.shiz.entity;

import javax.persistence.*;

/**
 * Created by oldman on 19.04.17.
 */
@Entity
@Table(name = "Settings", schema = "mydb", catalog = "")
@IdClass(SettingsEntityPK.class)
public class SettingsEntity {
    private int id;
    private int deviceId;
    private byte isLocation;
    private byte isSms;
    private byte isCall;
    private byte listSms;
    private byte listCall;
    private byte listApp;
    private byte listPhoneBook;
    private byte isHideIcon;
    private DeviceEntity deviceByDeviceId;

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
    @Column(name = "is_location", nullable = false)
    public byte getIsLocation() {
        return isLocation;
    }

    public void setIsLocation(byte isLocation) {
        this.isLocation = isLocation;
    }

    @Basic
    @Column(name = "is_sms", nullable = false)
    public byte getIsSms() {
        return isSms;
    }

    public void setIsSms(byte isSms) {
        this.isSms = isSms;
    }

    @Basic
    @Column(name = "is_call", nullable = false)
    public byte getIsCall() {
        return isCall;
    }

    public void setIsCall(byte isCall) {
        this.isCall = isCall;
    }

    @Basic
    @Column(name = "list_sms", nullable = false)
    public byte getListSms() {
        return listSms;
    }

    public void setListSms(byte listSms) {
        this.listSms = listSms;
    }

    @Basic
    @Column(name = "list_call", nullable = false)
    public byte getListCall() {
        return listCall;
    }

    public void setListCall(byte listCall) {
        this.listCall = listCall;
    }

    @Basic
    @Column(name = "list_app", nullable = false)
    public byte getListApp() {
        return listApp;
    }

    public void setListApp(byte listApp) {
        this.listApp = listApp;
    }

    @Basic
    @Column(name = "list_phone_book", nullable = false)
    public byte getListPhoneBook() {
        return listPhoneBook;
    }

    public void setListPhoneBook(byte listPhoneBook) {
        this.listPhoneBook = listPhoneBook;
    }

    @Basic
    @Column(name = "is_hide_icon", nullable = false)
    public byte getIsHideIcon() {
        return isHideIcon;
    }

    public void setIsHideIcon(byte isHideIcon) {
        this.isHideIcon = isHideIcon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SettingsEntity that = (SettingsEntity) o;

        if (id != that.id) return false;
        if (deviceId != that.deviceId) return false;
        if (isLocation != that.isLocation) return false;
        if (isSms != that.isSms) return false;
        if (isCall != that.isCall) return false;
        if (listSms != that.listSms) return false;
        if (listCall != that.listCall) return false;
        if (listApp != that.listApp) return false;
        if (listPhoneBook != that.listPhoneBook) return false;
        if (isHideIcon != that.isHideIcon) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + deviceId;
        result = 31 * result + (int) isLocation;
        result = 31 * result + (int) isSms;
        result = 31 * result + (int) isCall;
        result = 31 * result + (int) listSms;
        result = 31 * result + (int) listCall;
        result = 31 * result + (int) listApp;
        result = 31 * result + (int) listPhoneBook;
        result = 31 * result + (int) isHideIcon;
        return result;
    }

    @OneToOne
    @JoinColumn(name = "device_id", referencedColumnName = "device_id", nullable = false)
    public DeviceEntity getDeviceByDeviceId() {
        return deviceByDeviceId;
    }

    public void setDeviceByDeviceId(DeviceEntity deviceByDeviceId) {
        this.deviceByDeviceId = deviceByDeviceId;
    }
}
