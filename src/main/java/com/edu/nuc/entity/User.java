package com.edu.nuc.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by macbookair on 2018/4/9.
 */
@Entity
@Table
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer uid;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private Integer power;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }
}
