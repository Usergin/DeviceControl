package com.shiz.model.data.event;

import java.util.Date;

/**
 * Created by OldMan on 19.05.2017.
 */
public class ServiceEvent <T extends BaseEvent> {
    private String area;
    private String event;
    private Date date;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}