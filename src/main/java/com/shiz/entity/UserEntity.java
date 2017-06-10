package com.shiz.entity;

import javax.persistence.*;
import java.util.Arrays;

/**
 * Created by OldMan on 09.06.2017.
 */
@Entity
@Table(name = "user", schema = "mydb")
public class UserEntity {
    private int idUser;
    private String login;
    private String password;
    private String username;
    private String departmen;
    private String rank;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_user", unique = true, nullable = false)
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Column(name = "login", nullable = false, length = 45)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "username", nullable = true, length = 45)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "department", nullable = true, length = 45)
    public String getDepartmen() {
        return departmen;
    }

    public void setDepartmen(String departmen) {
        this.departmen = departmen;
    }

    @Basic
    @Column(name = "rank", nullable = true, length = 45)
    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (idUser != that.idUser) return false;
        if (login != null ? !login.equals(that.login) : that.login != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (departmen != null ? !departmen.equals(that.departmen) : that.departmen != null) return false;
        if (rank != null ? !rank.equals(that.rank) : that.rank != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idUser;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (departmen != null ? departmen.hashCode() : 0);
        result = 31 * result + (rank != null ? rank.hashCode() : 0);
        return result;
    }
}
