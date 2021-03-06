package com.edu.nuc.jpa;

import com.edu.nuc.entity.Product;
import com.edu.nuc.entity.ProductType;

import java.util.List;

/**
 * Created by macbookair on 2018/4/10.
 */
public interface ProductJPA extends BaseJPA<Product,Integer>{
    List<Product> findByDiscountstatusIsNot(Integer state);

    List<Product> findByProductType(ProductType productType);
}
