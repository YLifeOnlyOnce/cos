package com.edu.nuc.jpa;

import com.edu.nuc.entity.OrderForm;
import com.edu.nuc.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderFormJpa extends BaseJPA<OrderForm,Integer> {
    List<OrderForm> findByStateOrderByPlaceTimeDesc(int state);
    @Query(value = "select o from com.edu.nuc.entity.OrderForm o where o.user.uid = :uid order by placeTime")
    List<OrderForm> selectByUserOrderByPlaceTimeDesc(@Param("uid")Integer uid);
    List<OrderForm> findByUserOrderByPlaceTimeDesc(User user);
    @Query(value = "select o from com.edu.nuc.entity.OrderForm o order by state asc, placeTime desc ")
    List<OrderForm> findAllOrderByState();
}
