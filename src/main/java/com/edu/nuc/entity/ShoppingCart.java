package com.edu.nuc.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 购物车实体类
 */
@Entity
@Table
public class ShoppingCart implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer scid;
    /**
     * 购物车记录所属用户
     */
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "uid" , referencedColumnName = "uid")
    private User user;
    /**
     * 购物车中商品
     */
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "pid",referencedColumnName = "pid")
    private Product product;
    /**
     * 商品数量
     */
    @Column
    private Integer conut;

    public Integer getScid() {
        return scid;
    }

    public void setScid(Integer scid) {
        this.scid = scid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getConut() {
        return conut;
    }

    public void setConut(Integer conut) {
        this.conut = conut;
    }
}
