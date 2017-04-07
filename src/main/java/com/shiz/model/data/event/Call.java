package com.shiz.model.data.event;

import com.shiz.model.data.BaseInfo;
import org.hibernate.annotations.Table;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by oldman on 05.04.17.
 */

public class Call extends BaseInfo {
    private String number;
    private String duration;
    private Date date;

    public Call() {
    }

    public Call(String number, String duration, Date date) {
        this.number = number;
        this.duration = duration;
        this.date = date;

    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}