package com.shiz.model;

/**
 * Created by oldman on 04.04.17.
 */
public class Location {
    private double longitude, latitude;
    private float accuracy;
    private long date;

    public Location(double longitude, double latitude, float accuracy, long date) {
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

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
