package com.shiz.entity;

import javax.persistence.*;

/**
 * Created by oldman on 19.04.17.
 */
@Entity
@Table(name = "contact_book", schema = "mydb")
@IdClass(ContactBookEntityPK.class)
public class ContactBookEntity {
    private int id;
    private int dev_db_id;
    private String number;
    private String workNumber;
    private String homeNumber;
    private String mainNumber;
    private String eMail;
    private String name;
    private String info;
    private DeviceEntity contactBookByDeviceId;

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
    @Column(name = "dev_db_id", nullable = false, length = 45)
    public int getDevDbId() {
        return dev_db_id;
    }

    public void setDevDbId(int dev_db_id) {
        this.dev_db_id = dev_db_id;
    }

    @Basic
    @Column(name = "number", length = 45)
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Basic
    @Column(name = "work_number", length = 45)
    public String getWorkNumber() {
        return workNumber;
    }

    public void setWorkNumber(String workNumber) {
        this.workNumber = workNumber;
    }

    @Basic
    @Column(name = "home_number", length = 45)
    public String getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(String homeNumber) {
        this.homeNumber = homeNumber;
    }

    @Basic
    @Column(name = "main_number", length = 45)
    public String getMainNumber() {
        return mainNumber;
    }

    public void setMainNumber(String mainNumber) {
        this.mainNumber = mainNumber;
    }

    @Basic
    @Column(name = "e_mail", length = 45)
    public String getEMail() {
        return eMail;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    @Basic
    @Column(name = "name", length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "info")
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

        ContactBookEntity that = (ContactBookEntity) o;

        if (id != that.id) return false;
        if (number != null ? !number.equals(that.number) : that.number != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return info != null ? info.equals(that.info) : that.info == null;
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
    public DeviceEntity getContactBookByDeviceId() {
        return contactBookByDeviceId;
    }

    public void setContactBookByDeviceId(DeviceEntity telBookByDeviceId) {
        this.contactBookByDeviceId = telBookByDeviceId;
    }

}
