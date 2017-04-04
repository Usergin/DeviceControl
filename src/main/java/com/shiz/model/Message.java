package com.shiz.model;

/**
 * Created by oldman on 04.04.17.
 */
public class Message {
    private int type_id;
    private String number;
    private String data;

    public Message(int type_id, String number, String data) {
        this.type_id = type_id;
        this.number = number;
        this.data = data;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
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
}
