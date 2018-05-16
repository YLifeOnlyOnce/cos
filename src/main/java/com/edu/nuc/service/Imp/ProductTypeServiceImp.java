package com.edu.nuc.service.Imp;

import com.edu.nuc.entity.Product;
import com.edu.nuc.entity.ProductType;
import com.edu.nuc.jpa.ProductJPA;
import com.edu.nuc.jpa.ProductTypeJPA;
import com.edu.nuc.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductTypeServiceImp implements ProductTypeService {
    @Autowired
    ProductTypeJPA productTypeJPA;
    @Autowired
    ProductJPA productJPA;
    @Override
    public boolean insert(ProductType productType) {
        productTypeJPA.save(productType);
        return true;
    }

    @Override
    public boolean delete(Integer ptid) {
        ProductType productType = new ProductType();
        productType.setPtid(ptid);
        List<Product> byProductType = productJPA.findByProductType(productType);
        if (byProductType != null && byProductType.size() != 0) {
            return false;
        } else {
            productTypeJPA.deleteById(ptid);
            return true;
        }
    }

    @Override
    public List<ProductType> findAll() {
        return productTypeJPA.findAll();
    }
}
