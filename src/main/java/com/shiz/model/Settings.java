package com.shiz.model;

/**
 * Created by oldman on 04.04.17.
 */
public class Settings {
    private boolean is_location, is_sms, is_call, is_snapshot, is_microphone;

    public Settings(boolean is_location, boolean is_sms, boolean is_call, boolean is_snapshot, boolean is_microphone) {
        this.is_location = is_location;
        this.is_sms = is_sms;
        this.is_call = is_call;
        this.is_snapshot = is_snapshot;
        this.is_microphone = is_microphone;
    }

    public boolean is_location() {
        return is_location;
    }

    public void setIs_location(boolean is_location) {
        this.is_location = is_location;
    }

    public boolean is_sms() {
        return is_sms;
    }

    public void setIs_sms(boolean is_sms) {
        this.is_sms = is_sms;
    }

    public boolean is_call() {
        return is_call;
    }

    public void setIs_call(boolean is_call) {
        this.is_call = is_call;
    }

    public boolean is_snapshot() {
        return is_snapshot;
    }

    public void setIs_snapshot(boolean is_snapshot) {
        this.is_snapshot = is_snapshot;
    }

    public boolean is_microphone() {
        return is_microphone;
    }

    public void setIs_microphone(boolean is_microphone) {
        this.is_microphone = is_microphone;
    }
}
