package com.edu.nuc.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单表
 */
@Entity
@Table
public class OrderForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer oid;
    /**
     * 订单商品
     */
    @OneToMany
    @JoinColumn(name = "oid")
    private List<OrderFormProduct> orderFormProducts;
    /**
     * 订单所属用户
     */
    @OneToOne
    @JoinColumn(name = "uid",referencedColumnName = "uid")
    private User user;
    /**
     * 订单状态
     */
    @Column
    private Integer state;
    /**
     * 下单
     */
    public static final int place = 0;
    /**
     * 派送
     */
    public static final int senfOf = 1;
    /**
     * 收货
     */
    public static final int take = 2;

    /**
     * 地址
     */
    @Column
    private String address;
    /**
     * 下单时间
     */
    @Column
    private Date placeTime;
    /**
     * 送出时间
     */
    @Column
    private Date senfOfTime;
    /**
     * 收货时间
     */
    @Column
    private Date takeTime;
    /**
     * 商品总价
     */
    @Column
    private BigDecimal totalPrices;

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public List<OrderFormProduct> getOrderFormProducts() {
        return orderFormProducts;
    }

    public void setOrderFormProducts(List<OrderFormProduct> orderFormProducts) {
        this.orderFormProducts = orderFormProducts;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getPlaceTime() {
        return placeTime;
    }

    public void setPlaceTime(Date placeTime) {
        this.placeTime = placeTime;
    }

    public Date getSenfOfTime() {
        return senfOfTime;
    }

    public void setSenfOfTime(Date senfOfTime) {
        this.senfOfTime = senfOfTime;
    }

    public Date getTakeTime() {
        return takeTime;
    }

    public void setTakeTime(Date takeTime) {
        this.takeTime = takeTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getTotalPrices() {
        return totalPrices;
    }

    public void setTotalPrices(BigDecimal totalPrices) {
        this.totalPrices = totalPrices;
    }
}
