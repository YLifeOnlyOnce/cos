package com.edu.nuc.entity;

import javax.persistence.*;

/**
 * 商品分类
 */
@Entity
@Table
public class ProductType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer ptid;
    @Column
    private String name;

    public Integer getPtid() {
        return ptid;
    }

    public void setPtid(Integer ptid) {
        this.ptid = ptid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
