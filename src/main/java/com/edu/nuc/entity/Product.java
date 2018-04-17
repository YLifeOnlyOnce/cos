package com.edu.nuc.entity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by macbookair on 2018/4/10.
 */
@Entity
@Table
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer pid;

    @Column
    private String pname;

    @Column
    private String pimg;

    @Column
    private BigDecimal price;
//    售价
    @Column
    private Integer salesvolume;
//    折扣价
    @Column
    private Integer discountprice;
//    是否打折状态
    @Column
    private Integer discountstatus;
    @Column
    private String descriptio;

    public String getDescriptio() {
        return descriptio;
    }

    public void setDescriptio(String descriptio) {
        this.descriptio = descriptio;
    }

    /**
     * 打折 ： 0
     * 不打折： 1
     */
    public static final Integer discount = 0;


    /**
     * 打折 ： 0
     * 不打折 ： 1
     */
    public static final Integer notdiscount = 1;

    public Integer getDiscountprice() {
        return discountprice;
    }

    public void setDiscountprice(Integer discountprice) {
        this.discountprice = discountprice;
    }

    public Integer getDiscountstatus() {
        return discountstatus;
    }

    public void setDiscountstatus(Integer discountstatus) {
        this.discountstatus = discountstatus;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPimg() {
        return pimg;
    }

    public void setPimg(String pimg) {
        this.pimg = pimg;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getSalesvolume() {
        return salesvolume;
    }

    public void setSalesvolume(Integer salesvolume) {
        this.salesvolume = salesvolume;
    }

    public Product(String pname, String pimg, BigDecimal price, Integer salesvolume) {
        this.pname = pname;
        this.pimg = pimg;
        this.price = price;
        this.salesvolume = salesvolume;
    }

    public Product() {
    }
}
