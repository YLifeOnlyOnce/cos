package com.edu.nuc.entity;

import org.hibernate.mapping.ToOne;

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
    @ManyToOne
    @JoinColumn(name = "pid",referencedColumnName = "pid")
    private Product product;
    /**
     * 商品数量
     */
    @Column
    private int count;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
