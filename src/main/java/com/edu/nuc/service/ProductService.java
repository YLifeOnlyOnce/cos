package com.edu.nuc.service;

import com.edu.nuc.entity.Product;

import java.util.List;
import java.util.Map;

/**
 * Created by macbookair on 2018/4/10.
 */
public interface ProductService {

    List<Product> findAll();

    /**
     * 上架商品
     * @param product
     */
    void putProduct(Product product);

    Product findPByPid(Integer pid);

    /**
     * 设置促销商品
     * @param pid 商品id
     * @param price 商品价格
     * @return 结果
     */
    boolean setPromotion(Integer pid, double price);

    /**
     * 取消促销
     * @param pid 商品id
     * @return 结果
     */
    boolean cancelPromotion(Integer pid);

    /**
     * 下架商品
     * @param pid 商品id
     * @return 结果
     */
    boolean soldOutProduct(Integer pid);
}
