package com.shiz.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by oldman on 07.05.17.
 */
@Entity
@Table(name = "Bell", schema = "mydb")
//@IdClass(CallEntityPK.class)
public class CallEntity {
    private int id;
    private String number;
    private Timestamp date;
    private int typeEventId;
    private int duration;
    private DeviceEntity callByDeviceId;

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
    @Column(name = "number", nullable = false, length = 45)
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Basic
    @Column(name = "duration", nullable = false, length = 45)
    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Basic
    @Column(name = "date", nullable = false)
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Basic
    @Column(name = "type_event_id", nullable = false)
    public int getTypeEventId() {
        return typeEventId;
    }

    public void setTypeEventId(int typeEventId) {
        this.typeEventId = typeEventId;
    }

    @ManyToOne
    @JoinColumn(name = "device_id")
    public DeviceEntity getCallByDeviceId() {
        return callByDeviceId;
    }

    public void setCallByDeviceId(DeviceEntity callByDeviceId) {
        this.callByDeviceId = callByDeviceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CallEntity that = (CallEntity) o;

        if (id != that.id) return false;
        if (typeEventId != that.typeEventId) return false;
        if (number != null ? !number.equals(that.number) : that.number != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + typeEventId;
        return result;
    }
}
