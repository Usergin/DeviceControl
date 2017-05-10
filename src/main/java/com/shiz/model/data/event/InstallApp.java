package com.shiz.model.data.event;

import java.util.Date;

/**
 * Created by oldman on 05.04.17.
 */
public class InstallApp extends BaseEvent {
    private String name;
    private String info;
    private Date date;

//    public InstallApp(String name, String info, Date date) {
//        this.name = name;
//        this.info = info;
//        this.date = date;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


}
