package com.edu.nuc.service.Imp;

import com.edu.nuc.entity.Product;
import com.edu.nuc.jpa.ProductJPA;
import com.edu.nuc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by macbookair on 2018/4/10.
 */
@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    ProductJPA productJPA;
    @Override
    public List<Product> findAll() {
        return productJPA.findAll();

    }

    @Override
    public void putProduct(Product product) {

        productJPA.save(product);
        return ;
    }
}
