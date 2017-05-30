package com.shiz.model.data;

/**
 * Created by oldman on 05.04.17.
 */
public class Contact extends BaseInfo {

    private int db_id;
    private String number;
    private String name;
    private String work_number;
    private String home_number;
    private String main_number;
    private String e_mail;
    private String info;

    public int getDbId() {
        return db_id;
    }

    public void setDbId(int db_id) {
        this.db_id = db_id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

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

    public String getWorkNumber() {
        return work_number;
    }

    public void setWorkNumber(String work_number) {
        this.work_number = work_number;
    }

    public String getHomeNumber() {
        return home_number;
    }

    public void setHomeNumber(String home_number) {
        this.home_number = home_number;
    }

    public String getMainNumber() {
        return main_number;
    }

    public void setMainNumber(String main_number) {
        this.main_number = main_number;
    }

    public String getEMail() {
        return e_mail;
    }

    public void setEMail(String eMail) {
        this.e_mail = eMail;
    }
}