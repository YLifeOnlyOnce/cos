package com.edu.nuc.jpa;

import com.edu.nuc.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductJPATest {
    @Autowired
    ProductJPA productJPA;


    @Test
    public void findByDiscountstatusIsNot() {
        List<Product> byDiscountstatusIsNot = productJPA.findByDiscountstatusIsNot(2);
        System.out.println(byDiscountstatusIsNot);

    }
}