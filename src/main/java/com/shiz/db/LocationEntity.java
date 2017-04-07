package com.shiz.db;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by oldman on 07.04.17.
 */
@Entity
@Table(name = "Location", schema = "mydb", catalog = "")
@IdClass(LocationEntityPK.class)
public class LocationEntity {
    private int id;
    private int deviceId;
    private double longitude;
    private double latitude;
    private double accurancy;
    private String method;
    private Timestamp date;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    @Column(name = "device_id", nullable = false)
    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    @Basic
    @Column(name = "longitude", nullable = false, precision = 0)
    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Basic
    @Column(name = "latitude", nullable = false, precision = 0)
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Basic
    @Column(name = "accurancy", nullable = false, precision = 0)
    public double getAccurancy() {
        return accurancy;
    }

    public void setAccurancy(double accurancy) {
        this.accurancy = accurancy;
    }

    @Basic
    @Column(name = "method", nullable = true, length = 45)
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Basic
    @Column(name = "date", nullable = false)
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LocationEntity that = (LocationEntity) o;

        if (id != that.id) return false;
        if (deviceId != that.deviceId) return false;
        if (Double.compare(that.longitude, longitude) != 0) return false;
        if (Double.compare(that.latitude, latitude) != 0) return false;
        if (Double.compare(that.accurancy, accurancy) != 0) return false;
        if (method != null ? !method.equals(that.method) : that.method != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + deviceId;
        temp = Double.doubleToLongBits(longitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(latitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(accurancy);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (method != null ? method.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
