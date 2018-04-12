package com.edu.nuc.jpa;

import com.edu.nuc.entity.OrderForm;
import com.edu.nuc.entity.User;

import java.util.List;

public interface OrderFormJpa extends BaseJPA<OrderForm,Integer> {
    List<OrderForm> findByUser(User user);

    List<OrderForm> findByState(int state);
}
