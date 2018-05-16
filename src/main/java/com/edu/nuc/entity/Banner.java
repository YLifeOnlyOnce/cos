package com.edu.nuc.entity;

import javax.persistence.*;

/**
 * 轮播图
 */
@Entity
@Table
public class Banner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer bid;
    @Column
    private String bimg;
    @OneToOne
    @JoinColumn(name = "pid",referencedColumnName = "pid")
    private Product product;

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public String getBimg() {
        return bimg;
    }

    public void setBimg(String bimg) {
        this.bimg = bimg;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
