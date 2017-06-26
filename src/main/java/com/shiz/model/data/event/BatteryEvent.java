package com.shiz.model.data.event;

import java.util.Date;

/**
 * Created by oldman on 05.04.17.
 */
public class BatteryEvent extends BaseEvent {
    private String level;
    private String status;
    private String battery_status;
    private String type_charging;
    private Date date;

    public BatteryEvent() {
    }

    public BatteryEvent(String level, String status, String battery_status, String type_charging, Date date) {
        this.level = level;
        this.status = status;
        this.battery_status = battery_status;
        this.type_charging = type_charging;
        this.date = date;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBattery_status() {
        return battery_status;
    }

    public void setBattery_status(String battery_status) {
        this.battery_status = battery_status;
    }

    public String getType_charging() {
        return type_charging;
    }

    public void setType_charging(String type_charging) {
        this.type_charging = type_charging;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
