package com.shiz.model.data.event;

import com.shiz.model.data.BaseInfo;

import java.util.Date;

/**
 * Created by oldman on 05.04.17.
 */
public class SMS extends BaseInfo {
    private String number;
    private String data;
    private Date date;

    public SMS(String number, String data, Date date) {
        this.number = number;
        this.data = data;
        this.date = date;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}