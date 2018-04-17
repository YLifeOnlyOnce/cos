package com.edu.nuc.jpa;

import com.edu.nuc.entity.Product;
import com.edu.nuc.entity.ShoppingCart;
import com.edu.nuc.entity.User;

import java.util.List;

/**
 * 购物车jpa
 */
public interface ShoppingCartJpa extends BaseJPA<ShoppingCart,Integer> {
    List<ShoppingCart> findByUser(User user);

    ShoppingCart findByUserAndProducts(User user, Product product);

}
