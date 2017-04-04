package com.shiz.model;

/**
 * Created by oldman on 04.04.17.
 */
public class Device {
    private int id;
    private String imei, model, serial_num;
    private Location location;

    public Device(int id, String imei, String model, String serial_num, Location location) {
        this.id = id;
        this.imei = imei;
        this.model = model;
        this.serial_num = serial_num;
        this.location = location;

    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerial_num() {
        return serial_num;
    }

    public void setSerial_num(String serial_num) {
        this.serial_num = serial_num;
    }

    public Location getLocation() {return location;}

    public void setLocation(Location location) {this.location = location;}
}
