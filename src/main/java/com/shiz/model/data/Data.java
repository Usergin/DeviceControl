package com.shiz.model.data;

import java.util.Date;

/**
 * Created by oldman on 05.04.17.
 */
public class Data {
    private BaseInfo info;
    private int type;
    private Date date;

    public Data(int type, BaseInfo info, Date date) {
        this.info = info;
        this.type = type;
        this.date = date;
    }

    public BaseInfo getInfo() {
        return info;
    }

    public void setInfo(BaseInfo info) {
        this.info = info;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
