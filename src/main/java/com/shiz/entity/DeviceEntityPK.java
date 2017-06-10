package com.shiz.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by oldman on 19.04.17.
 */
public class DeviceEntityPK implements Serializable {
    private int id;

    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeviceEntityPK that = (DeviceEntityPK) o;

        if (id != that.id) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        return result;
    }
}
