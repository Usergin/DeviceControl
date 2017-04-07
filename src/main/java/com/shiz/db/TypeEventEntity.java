package com.shiz.db;

import javax.persistence.*;

/**
 * Created by oldman on 07.04.17.
 */
@Entity
@Table(name = "TypeEvent", schema = "mydb", catalog = "")
public class TypeEventEntity {
    private int idTypeEvent;
    private String event;

    @Id
    @Column(name = "idTypeEvent", nullable = false)
    public int getIdTypeEvent() {
        return idTypeEvent;
    }

    public void setIdTypeEvent(int idTypeEvent) {
        this.idTypeEvent = idTypeEvent;
    }

    @Basic
    @Column(name = "Event", nullable = false, length = 45)
    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TypeEventEntity that = (TypeEventEntity) o;

        if (idTypeEvent != that.idTypeEvent) return false;
        if (event != null ? !event.equals(that.event) : that.event != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idTypeEvent;
        result = 31 * result + (event != null ? event.hashCode() : 0);
        return result;
    }
}
