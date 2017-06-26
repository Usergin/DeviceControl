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

    public int getDb_id() {
        return db_id;
    }

    public void setDb_id(int db_id) {
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

    public String getWork_number() {
        return work_number;
    }

    public void setWork_number(String work_number) {
        this.work_number = work_number;
    }

    public String getHome_number() {
        return home_number;
    }

    public void setHome_number(String home_number) {
        this.home_number = home_number;
    }

    public String getMain_number() {
        return main_number;
    }

    public void setMain_number(String main_number) {
        this.main_number = main_number;
    }

    public String getE_mail() {
        return e_mail;
    }

    public void setE_mail(String eMail) {
        this.e_mail = eMail;
    }

}