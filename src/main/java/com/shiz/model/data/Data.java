package com.shiz.model.data;

import com.shiz.model.data.event.Call;
import com.shiz.model.request.BaseRequest;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by oldman on 05.04.17.
 */
public class Data {
    private BaseInfo info;
    private int type;
    private Date date;

    public Data(int type, Call info, Date date) {
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

    public Date getDate() {return date;}

    public void setDate(Date date) {this.date = date;}
}