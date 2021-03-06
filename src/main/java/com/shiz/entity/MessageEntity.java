package com.shiz.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by oldman on 19.04.17.
 */
@Entity
@Table(name = "message", schema = "mydb")
@IdClass(MessageEntityPK.class)
public class MessageEntity {
    private int id;
    private String number;
    private String data;
    private Date date;
    private int typeEventId;
    private DeviceEntity messageByDeviceId;

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
    @Column(name = "data", nullable = true, length = -1)
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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
    public DeviceEntity getMessageByDeviceId() {
        return messageByDeviceId;
    }

    public void setMessageByDeviceId(DeviceEntity msgByDeviceId) {
        this.messageByDeviceId = msgByDeviceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessageEntity that = (MessageEntity) o;

        if (id != that.id) return false;
        if (typeEventId != that.typeEventId) return false;
        if (number != null ? !number.equals(that.number) : that.number != null) return false;
        if (data != null ? !data.equals(that.data) : that.data != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (data != null ? data.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + typeEventId;
        return result;
    }
}
