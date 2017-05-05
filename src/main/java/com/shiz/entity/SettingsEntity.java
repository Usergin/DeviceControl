package com.shiz.entity;

import javax.persistence.*;

/**
 * Created by oldman on 19.04.17.
 */
@Entity
@Table(name = "Settings", schema = "mydb")
@IdClass(SettingsEntityPK.class)
public class SettingsEntity {
    private int id;
    private boolean isLocation;
    private int locationMode;
    private boolean isSms;
    private boolean isCall;
    private boolean listSms;
    private boolean listCall;
    private boolean listApp;
    private boolean listPhoneBook;
    private boolean isHideIcon;
    private boolean isService;
    private boolean isAirplaneMode;
    private boolean isWifi;
    private boolean isScreen;
    private int password;
    private DeviceEntity deviceByDeviceId;

    public SettingsEntity() {
    }

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "is_airplane_mode", nullable = false)
    public boolean isAirplaneMode() {
        return isAirplaneMode;
    }

    public void setAirplaneMode(boolean airplaneMode) {
        isAirplaneMode = airplaneMode;
    }

    @Basic
    @Column(name = "is_wifi", nullable = false)
    public boolean isWifi() {
        return isWifi;
    }

    public void setWifi(boolean wifi) {
        isWifi = wifi;
    }

    @Basic
    @Column(name = "is_screen", nullable = false)
    public boolean isScreen() {
        return isScreen;
    }

    public void setScreen(boolean screen) {
        isScreen = screen;
    }

    @Basic
    @Column(name = "password", nullable = false)
    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    @Basic
    @Column(name = "is_location", nullable = false)
    public boolean getIsLocation() {
        return isLocation;
    }

    public void setIsLocation(boolean isLocation) {
        this.isLocation = isLocation;
    }

    @Basic
    @Column(name = "location_mode", nullable = false)
    public int getLocationMode() {
        return locationMode;
    }

    public void setLocationMode(int locationMode) {
        this.locationMode = locationMode;
    }

    @Basic
    @Column(name = "is_service", nullable = false)
    public boolean getIsService() {
        return isService;
    }

    public void setIsService(boolean isService) {
        this.isService = isService;
    }

    @Basic
    @Column(name = "is_sms", nullable = false)
    public boolean getIsSms() {
        return isSms;
    }

    public void setIsSms(boolean isSms) {
        this.isSms = isSms;
    }

    @Basic
    @Column(name = "is_call", nullable = false)
    public boolean getIsCall() {
        return isCall;
    }

    public void setIsCall(boolean isCall) {
        this.isCall = isCall;
    }

    @Basic
    @Column(name = "list_sms", nullable = false)
    public boolean getListSms() {
        return listSms;
    }

    public void setListSms(boolean listSms) {
        this.listSms = listSms;
    }

    @Basic
    @Column(name = "list_call", nullable = false)
    public boolean getListCall() {
        return listCall;
    }

    public void setListCall(boolean listCall) {
        this.listCall = listCall;
    }

    @Basic
    @Column(name = "list_app", nullable = false)
    public boolean getListApp() {
        return listApp;
    }

    public void setListApp(boolean listApp) {
        this.listApp = listApp;
    }

    @Basic
    @Column(name = "list_phone_book", nullable = false)
    public boolean getListPhoneBook() {
        return listPhoneBook;
    }

    public void setListPhoneBook(boolean listPhoneBook) {
        this.listPhoneBook = listPhoneBook;
    }

    @Basic
    @Column(name = "is_hide_icon", nullable = false)
    public boolean getIsHideIcon() {
        return isHideIcon;
    }

    public void setIsHideIcon(boolean isHideIcon) {
        this.isHideIcon = isHideIcon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SettingsEntity that = (SettingsEntity) o;

        if (id != that.id) return false;
        if (isLocation != that.isLocation) return false;
        if (isSms != that.isSms) return false;
        if (isCall != that.isCall) return false;
        if (listSms != that.listSms) return false;
        if (listCall != that.listCall) return false;
        if (listApp != that.listApp) return false;
        if (listPhoneBook != that.listPhoneBook) return false;
        if (isHideIcon != that.isHideIcon) return false;
        if (isAirplaneMode != that.isAirplaneMode) return false;
        if (isWifi != that.isWifi) return false;
        if (isScreen != that.isScreen) return false;
        if (isService != that.isService) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @OneToOne
    @JoinColumn(name = "device_id")
    public DeviceEntity getSettingsDeviceByDeviceId() {
        return deviceByDeviceId;
    }

    public void setSettingsDeviceByDeviceId(DeviceEntity deviceByDeviceId) {
        this.deviceByDeviceId = deviceByDeviceId;
    }

    public static SettingsEntity.Builder newBuilder() {
        return new SettingsEntity.Builder();
    }

    private SettingsEntity(SettingsEntity.Builder builder) {
        setIsLocation(builder.is_location);
        setIsSms(builder.is_sms);
        setIsCall(builder.is_call);
        setListSms(builder.list_sms);
        setListCall(builder.list_call);
        setListApp(builder.list_app);
        setListPhoneBook(builder.list_phone_book);
        setIsHideIcon(builder.hide_icon);
        setLocationMode(builder.location_mode);
        setIsService(builder.is_service);
        setPassword(builder.password);
        setScreen(builder.is_screen);
        setWifi(builder.is_wifi);
        setAirplaneMode(builder.is_airplane_mode);

    }

    public static final class Builder {
        private boolean is_location;
        private boolean is_sms;
        private boolean is_call;
        private boolean list_sms;
        private boolean list_call;
        private boolean list_app;
        private boolean list_phone_book;
        private boolean hide_icon;
        private int location_mode;
        private boolean is_service;
        private boolean is_airplane_mode;
        private boolean is_wifi;
        private boolean is_screen;
        private int password;

        private Builder() {
        }

        public SettingsEntity.Builder is_airplane_mode(boolean val) {
            is_airplane_mode = val;
            return this;
        }

        public SettingsEntity.Builder is_wifi(boolean val) {
            is_wifi = val;
            return this;
        }

        public SettingsEntity.Builder is_screen(boolean val) {
            is_screen = val;
            return this;
        }

        public SettingsEntity.Builder password(int val) {
            password = val;
            return this;
        }

        public SettingsEntity.Builder is_location(boolean val) {
            is_location = val;
            return this;
        }

        public SettingsEntity.Builder is_service(boolean val) {
            is_service = val;
            return this;
        }

        public SettingsEntity.Builder location_mode(int val) {
            location_mode = val;
            return this;
        }

        public SettingsEntity.Builder is_sms(boolean val) {
            is_sms = val;
            return this;
        }

        public SettingsEntity.Builder is_call(boolean val) {
            is_call = val;
            return this;
        }

        public SettingsEntity.Builder list_sms(boolean val) {
            list_sms = val;
            return this;
        }

        public SettingsEntity.Builder list_call(boolean val) {
            list_call = val;
            return this;
        }

        public SettingsEntity.Builder list_app(boolean val) {
            list_app = val;
            return this;
        }

        public SettingsEntity.Builder list_phone_book(boolean val) {
            list_phone_book = val;
            return this;
        }

        public SettingsEntity.Builder hide_icon(boolean val) {
            hide_icon = val;
            return this;
        }

        public SettingsEntity build() {
            return new SettingsEntity(this);
        }
    }
}
