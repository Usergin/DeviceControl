package com.shiz.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by oldman on 19.04.17.
 */
public class NetworkStatusEntityPK implements Serializable {
    private int id;

    @Column(name = "id", nullable = false)
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

        NetworkStatusEntityPK that = (NetworkStatusEntityPK) o;

        if (id != that.id) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        return result;
    }
}
