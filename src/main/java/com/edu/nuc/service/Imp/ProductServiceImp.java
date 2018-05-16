package com.edu.nuc.service.Imp;

import com.edu.nuc.entity.Product;
import com.edu.nuc.entity.ProductType;
import com.edu.nuc.jpa.ProductJPA;
import com.edu.nuc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Created by macbookair on 2018/4/10.
 */
@Service
@Transactional(rollbackFor = Exception.class )
public class ProductServiceImp implements ProductService {
    @Autowired
    ProductJPA productJPA;

    @Override
    public List<Product> findAll() {
        return productJPA.findByDiscountstatusIsNot(2);

    }

    @Override
    public Product findPByPid(Integer pid) {
        Product product = productJPA.getOne(pid);
        return product;
    }

    @Override
    public void putProduct(Product product) {
        Product save = productJPA.save(product);
    }

    @Override
    public boolean setPromotion(Integer pid, double price) {
        Product one = productJPA.getOne(pid);
        int discountstatus = one.getDiscountstatus();
        if (discountstatus != Product.notdiscount) {
            //订单状态不为不促销
            return false;
        }
        one.setDiscountprice(new BigDecimal(price));
        one.setDiscountstatus(Product.discount);
        productJPA.save(one);
        return true;
    }

    @Override
    public boolean cancelPromotion(Integer pid) {
        Product one = productJPA.getOne(pid);
        int discountstatus = one.getDiscountstatus();
        if (discountstatus != Product.discount) {
            //订单状态不为促销
            return false;
        }
        one.setDiscountprice(new BigDecimal(0));
        one.setDiscountstatus(Product.notdiscount);
        productJPA.save(one);
        return true;
    }

    @Override
    public boolean soldOutProduct(Integer pid) {
        Product one = productJPA.getOne(pid);
        one.setDiscountstatus(Product.soldOut);
        productJPA.save(one);
        return true;
    }

    @Override
    public List<Product> findByProductType(ProductType productType) {
        return productJPA.findByProductType(productType);
    }


}
