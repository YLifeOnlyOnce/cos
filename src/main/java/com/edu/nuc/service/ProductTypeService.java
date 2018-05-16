package com.edu.nuc.service;

import com.edu.nuc.entity.ProductType;

import java.util.List;

/**
 * 商品分类业务逻辑
 */
public interface ProductTypeService {
    /**
     * 添加商品类别
     * @param productType
     * @return
     */
    boolean insert(ProductType productType);

    /**
     * 删除商品类别
     * @param ptid
     * @return
     */
    boolean delete(Integer ptid);

    /**
     * 查询所有商品类别
     * @return
     */
    List<ProductType> findAll();
}
