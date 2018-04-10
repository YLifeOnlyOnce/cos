package com.edu.nuc.service;

import com.edu.nuc.entity.Product;

import java.util.List;
import java.util.Map;

/**
 * Created by macbookair on 2018/4/10.
 */
public interface ProductService {

    List<Product> findAll();
    void putProduct(Product product);
}
