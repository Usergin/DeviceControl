package com.shiz.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by oldman on 19.04.17.
 */
@Entity
@Table(name = "location", schema = "mydb")
@IdClass(LocationEntityPK.class)
public class LocationEntity {
    private int id;
    private double longitude;
    private double latitude;
    private float accuracy;
    private String method;
    private Date date;
    private DeviceEntity locationByDeviceId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "accuracy", nullable = false, precision = 0)
    public float getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(float accurancy) {
        this.accuracy = accurancy;
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
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @ManyToOne
    @JoinColumn(name = "device_id")
    public DeviceEntity getLocationByDeviceId() {
        return locationByDeviceId;
    }

    public void setLocationByDeviceId(DeviceEntity locationByDeviceId) {
        this.locationByDeviceId = locationByDeviceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LocationEntity that = (LocationEntity) o;

        if (id != that.id) return false;
        if (Double.compare(that.longitude, longitude) != 0) return false;
        if (Double.compare(that.latitude, latitude) != 0) return false;
        if (Float.compare(that.accuracy, accuracy) != 0) return false;
        if (method != null ? !method.equals(that.method) : that.method != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        temp = Double.doubleToLongBits(longitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(latitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Float.floatToIntBits(accuracy);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (method != null ? method.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
