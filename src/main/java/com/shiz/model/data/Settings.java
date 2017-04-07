package com.shiz.model.data;

/**
 * Created by oldman on 05.04.17.
 */
public class Settings {
    private boolean is_location;
    private boolean is_sms;
    private boolean is_call;
    private boolean list_sms;
    private boolean list_call;
    private boolean list_app;
    private boolean list_phone_book;
    private boolean hide_icon;

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
    }
    public static Builder newBuilder() {
        return new Builder();
    }

    public boolean isLocation() {
        return is_location;
    }

    public boolean isHideIcon() {
        return hide_icon;
    }

    public void setHideIcon(boolean hide_icon) {
        this.hide_icon = hide_icon;
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


    public static final class Builder {
        private boolean is_location;
        private boolean is_sms;
        private boolean is_call;
        private boolean list_sms;
        private boolean list_call;
        private boolean list_app;
        private boolean list_phone_book;
        private boolean hide_icon;


        private Builder() {
        }

        public Builder is_location(boolean val) {
            is_location = val;
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
