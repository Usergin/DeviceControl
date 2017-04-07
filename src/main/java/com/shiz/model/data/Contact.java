package com.shiz.model.data;

/**
 * Created by oldman on 05.04.17.
 */
public class Contact extends BaseInfo {
    private String number;
    private String name;
    private String info;

    public Contact(String number, String name, String info) {
        this.number = number;
        this.name = name;
        this.info = info;
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
}