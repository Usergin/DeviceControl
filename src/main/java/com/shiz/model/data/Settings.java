package com.shiz.model.data;

/**
 * Created by OldMan on 18.06.2017.
 */
public class Settings {
    private boolean location;
    private int location_mode;
    private boolean sms;
    private boolean bell;
    private boolean sms_list;
    private boolean call_list;
    private boolean app_list;
    private boolean contact_book;
    private boolean hide_icon;
    private boolean service;
    private int airplane_mode;
    private int wifi;
    private int screen;
    private boolean flash;
    private boolean vibrate;
    private int sound;
    private boolean reboot;
    private boolean shut_down;
    private String rm_apps;
    private int passwd;
    private long sync_time;


    private Settings(Builder builder) {
        setLocation(builder.location);
        setSms(builder.sms);
        setBell(builder.call);
        setSms_list(builder.list_sms);
        setCall_list(builder.list_call);
        setApp_list(builder.list_app);
        setContact_book(builder.contact_book);
        setHide_icon(builder.hide_icon);
        setLocation_mode(builder.location_mode);
        setService(builder.service);
        setPasswd(builder.password);
        setScreen(builder.screen);
        setWifi(builder.wifi);
        setAirplane_mode(builder.airplane_mode);
        setReboot(builder.reboot);
        setShut_down(builder.shut_down);
        setRm_apps(builder.rm_apps);
        setFlash(builder.flash);
        setSound(builder.sound);
        setVibrate(builder.vibrate);
        setSync_time(builder.sync_time);
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

    public boolean isShut_down() {
        return shut_down;
    }

    public void setShut_down(boolean shut_down) {
        this.shut_down = shut_down;
    }

    public String getRm_apps() {
        return rm_apps;
    }

    public void setRm_apps(String rm_apps) {
        this.rm_apps = rm_apps;
    }

    public int getPasswd() {
        return passwd;
    }

    public void setPasswd(int passwd) {
        this.passwd = passwd;
    }

    public int getScreen() {
        return screen;
    }

    public void setScreen(int is_screen) {
        this.screen = is_screen;
    }

    public int getWifi() {
        return wifi;
    }

    public void setWifi(int is_wifi) {
        this.wifi = is_wifi;
    }

    public int getAirplane_mode() {
        return airplane_mode;
    }

    public void setAirplane_mode(int airplane_mode) {
        this.airplane_mode = airplane_mode;
    }

    public boolean getFlash() {
        return flash;
    }

    public void setFlash(boolean flash) {
        this.flash = flash;
    }

    public boolean getVibrate() {
        return vibrate;
    }

    public void setVibrate(boolean vibrate) {
        this.vibrate = vibrate;
    }

    public int getSound() {
        return sound;
    }

    public void setSound(int sound) {
        this.sound = sound;
    }

    public long getSync_time() {
        return sync_time;
    }

    public void setSync_time(long sync_time) {
        this.sync_time = sync_time;
    }

    public boolean isLocation() {
        return location;
    }

    public void setLocation(boolean is_location) {
        this.location = is_location;
    }

    public boolean isHide_icon() {
        return hide_icon;
    }

    public void setHide_icon(boolean is_hide_icon) {
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

    public boolean isSms_list() {
        return sms_list;
    }

    public void setSms_list(boolean list_sms) {
        this.sms_list = list_sms;
    }

    public boolean isCall_list() {
        return call_list;
    }

    public void setCall_list(boolean list_call) {
        this.call_list = list_call;
    }

    public boolean isApp_list() {
        return app_list;
    }

    public void setApp_list(boolean list_app) {
        this.app_list = list_app;
    }

    public boolean isContact_book() {
        return contact_book;
    }

    public void setContact_book(boolean contact_book) {
        this.contact_book = contact_book;
    }

    public int getLocation_mode() {
        return location_mode;
    }

    public void setLocation_mode(int location_mode) {
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
        private int airplane_mode;
        private int wifi;
        private int screen;
        private boolean reboot;
        private boolean shut_down;
        private String rm_apps;
        private int password;
        private long sync_time;
        private boolean flash;
        private boolean vibrate;
        private int sound;


        private Builder() {
        }

        public Builder sync_time(long val) {
            sync_time = val;
            return this;
        }

        public Builder flash(boolean flash) {
            flash = flash;
            return this;
        }

        public Builder vibrate(boolean val) {
            vibrate = val;
            return this;
        }

        public Builder sound(int val) {
            sound = val;
            return this;
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

        public Builder airplane_mode(int val) {
            airplane_mode = val;
            return this;
        }

        public Builder wifi(int val) {
            wifi = val;
            return this;
        }

        public Builder screen(int val) {
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
