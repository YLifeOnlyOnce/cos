package com.edu.nuc.jpa;

import com.edu.nuc.entity.Product;
import com.edu.nuc.entity.ShoppingCart;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by macbookair on 2018/4/17.
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class ShoppingCartJpaTest {
    @Autowired
    ShoppingCartJpa shoppingCartJpa;

    @Test
    public void findByUserAndProducts() throws Exception {
        Product product = new Product();
        product.setPid(27);
        ShoppingCart byUserAndProducts = shoppingCartJpa.findByUserAndProduct(null, product);
        System.out.println(byUserAndProducts);
    }

}