package com.shiz.model.data;

/**
 * Created by oldman on 05.04.17.
 */
public class Settings {
    private boolean location;
    private int location_mode;
    private boolean sms;
    private boolean bell;
    private boolean list_sms;
    private boolean list_call;
    private boolean list_app;
    private boolean contact_book;
    private boolean hide_icon;
    private boolean service;
    private boolean airplane_mode;
    private boolean wifi;
    private boolean screen;
    private boolean reboot;
    private boolean shut_down;
    private String rm_apps;
    private int passwd;

    public Settings() {
    }

    private Settings(Builder builder) {
        setLocation(builder.location);
        setSms(builder.sms);
        setBell(builder.call);
        setListSms(builder.list_sms);
        setListCall(builder.list_call);
        setListApp(builder.list_app);
        setContactBook(builder.contact_book);
        setHideIcon(builder.hide_icon);
        setLocationMode(builder.location_mode);
        setService(builder.service);
        setPasswd(builder.password);
        setScreen(builder.screen);
        setWifi(builder.wifi);
        setAirplaneMode(builder.airplane_mode);
        setReboot(builder.reboot);
        setShutDown(builder.shut_down);
        setRmApps(builder.rm_apps);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public boolean isReboot() {
        return reboot;
    }

    public void setReboot(boolean reboot) {
        this.reboot = reboot;
    }

    public boolean isShutDown() {
        return shut_down;
    }

    public void setShutDown(boolean shut_down) {
        this.shut_down = shut_down;
    }

    public String getRmApps() {
        return rm_apps;
    }

    public void setRmApps(String rm_apps) {
        this.rm_apps = rm_apps;
    }

    public int getPasswd() {
        return passwd;
    }

    public void setPasswd(int passwd) {
        this.passwd = passwd;
    }

    public boolean isScreen() {
        return screen;
    }

    public void setScreen(boolean is_screen) {
        this.screen = is_screen;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean is_wifi) {
        this.wifi = is_wifi;
    }

    public boolean isAirplaneMode() {
        return airplane_mode;
    }

    public void setAirplaneMode(boolean airplane_mode) {
        this.airplane_mode = airplane_mode;
    }


    public boolean isLocation() {
        return location;
    }

    public void setLocation(boolean is_location) {
        this.location = is_location;
    }

    public boolean isHideIcon() {
        return hide_icon;
    }

    public void setHideIcon(boolean is_hide_icon) {
        this.hide_icon = is_hide_icon;
    }

    public boolean isSms() {
        return sms;
    }

    public void setSms(boolean is_sms) {
        this.sms = is_sms;
    }

    public boolean isBell() {
        return bell;
    }

    public void setBell(boolean is_call) {
        this.bell = is_call;
    }

    public boolean isListSms() {
        return list_sms;
    }

    public void setListSms(boolean list_sms) {
        this.list_sms = list_sms;
    }

    public boolean isListCall() {
        return list_call;
    }

    public void setListCall(boolean list_call) {
        this.list_call = list_call;
    }

    public boolean isListApp() {
        return list_app;
    }

    public void setListApp(boolean list_app) {
        this.list_app = list_app;
    }

    public boolean isContactBook() {
        return contact_book;
    }

    public void setContactBook(boolean contact_book) {
        this.contact_book = contact_book;
    }

    public int isLocationMode() {
        return location_mode;
    }

    public void setLocationMode(int location_mode) {
        this.location_mode = location_mode;
    }

    public boolean isService() {
        return service;
    }

    public void setService(boolean is_service) {
        this.service = is_service;
    }

    public static final class Builder {
        private boolean location;
        private boolean sms;
        private boolean call;
        private boolean list_sms;
        private boolean list_call;
        private boolean list_app;
        private boolean contact_book;
        private boolean hide_icon;
        private int location_mode;
        private boolean service;
        private boolean airplane_mode;
        private boolean wifi;
        private boolean screen;
        private boolean reboot;
        private boolean shut_down;
        private String rm_apps;
        private int password;

        private Builder() {
        }

        public Builder reboot(boolean val) {
            reboot = val;
            return this;
        }

        public Builder shut_down(boolean val) {
            shut_down = val;
            return this;
        }

        public Builder rm_apps(String val) {
            rm_apps = val;
            return this;
        }

        public Builder airplane_mode(boolean val) {
            airplane_mode = val;
            return this;
        }

        public Builder wifi(boolean val) {
            wifi = val;
            return this;
        }

        public Builder screen(boolean val) {
            screen = val;
            return this;
        }

        public Builder password(int val) {
            password = val;
            return this;
        }

        public Builder location(boolean val) {
            location = val;
            return this;
        }

        public Builder service(boolean val) {
            service = val;
            return this;
        }

        public Builder location_mode(int val) {
            location_mode = val;
            return this;
        }

        public Builder sms(boolean val) {
            sms = val;
            return this;
        }

        public Builder call(boolean val) {
            call = val;
            return this;
        }

        public Builder list_sms(boolean val) {
            list_sms = val;
            return this;
        }

        public Builder list_call(boolean val) {
            list_call = val;
            return this;
        }

        public Builder list_app(boolean val) {
            list_app = val;
            return this;
        }

        public Builder contact_book(boolean val) {
            contact_book = val;
            return this;
        }

        public Builder hide_icon(boolean val) {
            hide_icon = val;
            return this;
        }

        public Settings build() {
            return new Settings(this);
        }
    }
}
