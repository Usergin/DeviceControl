package com.shiz.model.data.event;

import com.shiz.model.data.BaseInfo;

import java.util.Date;

/**
 * Created by oldman on 05.04.17.
 */
public class Location extends BaseInfo {
    private double longitude, latitude;
    private float accuracy;
    private Date date;

    public Location(double longitude, double latitude, float accuracy, Date date) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.accuracy = accuracy;
        this.date = date;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public float getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(float accuracy) {
        this.accuracy = accuracy;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
