package com.edu.nuc.jpa;

import com.edu.nuc.entity.OrderForm;
import com.edu.nuc.entity.OrderFormProduct;
import com.edu.nuc.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderFormJpaTest {
    @Autowired
    OrderFormJpa orderFormJpa;
    @Test
    public void findByUserOrderByPlaceTimeDesc() {
        User user = new User();
        user.setUid(6);
        List<OrderForm> byUserOrderByPlaceTimeDesc = orderFormJpa.findByUserOrderByPlaceTimeDesc(user);
        System.out.println(byUserOrderByPlaceTimeDesc);
    }

    @Test
    public void selectByUserOrderByPlaceTimeDesc() {
        List<OrderForm> orderForms = orderFormJpa.selectByUserOrderByPlaceTimeDesc(6);
        System.out.println(orderForms);
    }
}