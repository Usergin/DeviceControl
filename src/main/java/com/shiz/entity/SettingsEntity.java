package com.shiz.entity;


import javax.persistence.*;


/**
 * Created by oldman on 19.04.17.
 */
@Entity
@Table(name = "settings", schema = "mydb")
@IdClass(SettingsEntityPK.class)
public class SettingsEntity {
    private int id;
    private boolean location;
    private int locationMode;
    private boolean sms;
    private boolean call;
    private boolean hideIcon;
    private boolean service;
    private int password;
    //one time settings
    private boolean listSms;
    private boolean listCall;
    private boolean listApp;
    private boolean contactBook;
    private boolean airplaneMode;
    private boolean wifi;
    private boolean screen;
    private boolean reboot;
    private boolean shutDown;
    private String rmApps;

    private DeviceEntity deviceByDeviceId;

    public SettingsEntity() {
    }

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
    @Column(name = "reboot", nullable = false, columnDefinition = "TINYINT(1)")
    public boolean isReboot() {
        return reboot;
    }

    public void setReboot(boolean reboot) {
        this.reboot = reboot;
    }

    @Basic
    @Column(name = "shut_down", nullable = false, columnDefinition = "TINYINT(1)")
    public boolean isShutDown() {
        return shutDown;
    }

    public void setShutDown(boolean shutDown) {
        this.shutDown = shutDown;
    }

    @Basic
    @Column(name = "rm_apps", nullable = false)
    public String getRmApps() {
        return rmApps;
    }

    public void setRmApps(String rmApps) {
        this.rmApps = rmApps;
    }

    @Basic
    @Column(name = "airplane_mode", nullable = false, columnDefinition = "TINYINT(1)")
    public boolean isAirplaneMode() {
        return airplaneMode;
    }

    public void setAirplaneMode(boolean airplaneMode) {
        this.airplaneMode = airplaneMode;
    }

    @Basic
    @Column(name = "wifi", nullable = false, columnDefinition = "TINYINT(1)")
    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    @Basic
    @Column(name = "screen", nullable = false, columnDefinition = "TINYINT(1)")
    public boolean isScreen() {
        return screen;
    }

    public void setScreen(boolean screen) {
        this.screen = screen;
    }

    @Basic
    @Column(name = "passwd", nullable = false)
    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    @Basic
    @Column(name = "location", nullable = false, columnDefinition = "TINYINT(1)")
    public boolean isLocation() {
        return location;
    }

    public void setLocation(boolean isLocation) {
        this.location = isLocation;
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
    @Column(name = "service", nullable = false, columnDefinition = "TINYINT(1)")
    public boolean isService() {
        return service;
    }

    public void setService(boolean isService) {
        this.service = isService;
    }

    @Basic
    @Column(name = "sms", nullable = false, columnDefinition = "TINYINT(1)")
    public boolean isSms() {
        return sms;
    }

    public void setSms(boolean isSms) {
        this.sms = isSms;
    }

    @Basic
    @Column(name = "bell", nullable = false, columnDefinition = "TINYINT(1)")
    public boolean isCall() {
        return call;
    }

    public void setCall(boolean isCall) {
        this.call = isCall;
    }

    @Basic
    @Column(name = "list_sms", nullable = false, columnDefinition = "TINYINT(1)")
    public boolean getListSms() {
        return listSms;
    }

    public void setListSms(boolean listSms) {
        this.listSms = listSms;
    }

    @Basic
    @Column(name = "list_call", nullable = false, columnDefinition = "TINYINT(1)")
    public boolean getListCall() {
        return listCall;
    }

    public void setListCall(boolean listCall) {
        this.listCall = listCall;
    }

    @Basic
    @Column(name = "list_app", nullable = false, columnDefinition = "TINYINT(1)")
    public boolean getListApp() {
        return listApp;
    }

    public void setListApp(boolean listApp) {
        this.listApp = listApp;
    }

    @Basic
    @Column(name = "contact_book", nullable = false, columnDefinition = "TINYINT(1)")
    public boolean getContactBook() {
        return contactBook;
    }

    public void setContactBook(boolean listPhoneBook) {
        this.contactBook = listPhoneBook;
    }

    @Basic
    @Column(name = "hide_icon", nullable = false, columnDefinition = "TINYINT(1)")
    public boolean isHideIcon() {
        return hideIcon;
    }

    public void setHideIcon(boolean isHideIcon) {
        this.hideIcon = isHideIcon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SettingsEntity that = (SettingsEntity) o;

        if (id != that.id) return false;
        if (location != that.location) return false;
        if (sms != that.sms) return false;
        if (call != that.call) return false;
        if (listSms != that.listSms) return false;
        if (listCall != that.listCall) return false;
        if (listApp != that.listApp) return false;
        if (contactBook != that.contactBook) return false;
        if (hideIcon != that.hideIcon) return false;
        if (airplaneMode != that.airplaneMode) return false;
        if (wifi != that.wifi) return false;
        if (screen != that.screen) return false;
        if (service != that.service) return false;
        if (deviceByDeviceId != that.deviceByDeviceId) return false;

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
}
