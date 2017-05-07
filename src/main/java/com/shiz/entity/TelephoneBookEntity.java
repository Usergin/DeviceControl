package com.shiz.entity;

import javax.persistence.*;

/**
 * Created by oldman on 19.04.17.
 */
@Entity
@Table(name = "TelephoneBook", schema = "mydb")
@IdClass(TelephoneBookEntityPK.class)
public class TelephoneBookEntity {
    private int id;
    private String number;
    private String name;
    private String info;
    private DeviceEntity telBookByDeviceId;
    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "number", nullable = true, length = 45)
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "info", nullable = true, length = -1)
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TelephoneBookEntity that = (TelephoneBookEntity) o;

        if (id != that.id) return false;
        if (number != null ? !number.equals(that.number) : that.number != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (info != null ? !info.equals(that.info) : that.info != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (info != null ? info.hashCode() : 0);
        return result;
    }
    @ManyToOne
    @JoinColumn(name = "device_id")
    public DeviceEntity getTelBookByDeviceId() {
        return telBookByDeviceId;
    }

    public void setTelBookByDeviceId(DeviceEntity telBookByDeviceId) {
        this.telBookByDeviceId = telBookByDeviceId;
    }

}
