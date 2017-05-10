package com.shiz.model.data.event;

import com.shiz.model.data.BaseInfo;
import org.hibernate.annotations.Table;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by oldman on 05.04.17.
 */

public class Call extends BaseEvent {
    private String number;
    private int duration;
    private Date date;
    private int type;

    public Call() {
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}