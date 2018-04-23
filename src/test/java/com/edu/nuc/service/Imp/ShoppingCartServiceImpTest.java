package com.edu.nuc.service.Imp;

import com.edu.nuc.entity.Product;
import com.edu.nuc.entity.ShoppingCart;
import com.edu.nuc.entity.User;
import com.edu.nuc.jpa.ProductJPA;
import com.edu.nuc.jpa.UserJPA;
import com.edu.nuc.service.ShoppingCartService;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

//@RunWith(SpringRunner.class)
//@SpringBootTest
//@FixMethodOrder(MethodSorters.JVM)
public class ShoppingCartServiceImpTest {
    @Autowired
    ShoppingCartService shoppingCartService;
    //    @Autowired
//    ShoppingCartJpa shoppingCartJpa;
    @Autowired
    UserJPA userJPA;
    @Autowired
    ProductJPA productJPA;

    User user = new User("1", "1", 10, new BigDecimal(1));
    Product product = new Product("test", "cospic/a.png", new BigDecimal(1), 1);
//    @Before
    public void before() {
        user = userJPA.save(user);
        product = productJPA.save(product);
    }

//    @Test
    public void insert() {
        ShoppingCart shoppingCart = shoppingCartService.insert(user, product.getPid(), 1);
        assertNotNull(shoppingCart);
        List<ShoppingCart> byUid = shoppingCartService.findByUid(user.getUid());
        assertNotNull(byUid);
        boolean b = shoppingCartService.altercount(shoppingCart.getScid(), 2);
        assertTrue(b);
        boolean del = shoppingCartService.del(shoppingCart.getScid());
        assertTrue(del);
    }

//    @After
    public void after() {
        userJPA.delete(user);
        productJPA.delete(product);
    }
}