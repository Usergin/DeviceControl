package com.shiz.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by OldMan on 08.05.2017.
 */
@Entity
@Table(name = "ServiceEvent", schema = "mydb", catalog = "")
@IdClass(ServiceEventEntityPK.class)
public class ServiceEventEntity {
    private int id;
    private DeviceEntity serviceEventByDeviceId;
    private String area;
    private String event;
    private Timestamp date;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "device_id")
    public DeviceEntity getServiceEventByDeviceId() {
        return serviceEventByDeviceId;
    }

    public void setServiceEventByDeviceId(DeviceEntity serviceEventByDeviceId) {
        this.serviceEventByDeviceId = serviceEventByDeviceId;
    }
    @Basic
    @Column(name = "area", nullable = false, length = 45)
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Basic
    @Column(name = "event", nullable = false, length = -1)
    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
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

        ServiceEventEntity that = (ServiceEventEntity) o;

        if (id != that.id) return false;
        if (area != null ? !area.equals(that.area) : that.area != null) return false;
        if (event != null ? !event.equals(that.event) : that.event != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (area != null ? area.hashCode() : 0);
        result = 31 * result + (event != null ? event.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
