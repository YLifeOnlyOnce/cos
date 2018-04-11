package com.edu.nuc.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

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

    @Column
    private BigDecimal balance;



    /**
     * 管理员 0
     * 普通用户 1
     */
    public static final Integer adminpower=0;
    /**
     * 管理员 0
     * 普通用户 1
     */
    public static final Integer userpower=1;


    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

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

    public User(String username, String password, Integer power, BigDecimal balance) {
        this.username = username;
        this.password = password;
        this.power = power;
        this.balance = balance;
    }

    public User() {
    }
}
