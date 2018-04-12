package com.edu.nuc.entity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 订单商品
 */
@Entity
@Table
public class OrderFormProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer opid;
    @OneToOne
    @JoinColumn(name = "pid",referencedColumnName = "pid")
    private Product product;
    /**
     * 商品数量
     */
    @Column
    private int conut;
    /**
     * 购买价格
     */
    @Column
    private BigDecimal price;

    public Integer getOpid() {
        return opid;
    }

    public void setOpid(Integer opid) {
        this.opid = opid;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getConut() {
        return conut;
    }

    public void setConut(int conut) {
        this.conut = conut;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
