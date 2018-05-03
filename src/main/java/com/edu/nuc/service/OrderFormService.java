package com.edu.nuc.service;

import com.edu.nuc.entity.OrderForm;
import com.edu.nuc.entity.User;

import java.util.List;

/**
 * 订单业务逻辑
 */
public interface OrderFormService {
    /**
     * 下单
     * @param orderForm 封装有前台传来的订单必要数据，包括订单的商品数组商品数量
     * @return 下单结果
     */
    boolean insertOrder(OrderForm orderForm);


    /**
     * 设置订单状态为已发出
     * @param oid 订单号
     * @return 执行结果
     */
    boolean setStateSenfOf(int oid );

    /**
     * 设置订单状态为确认签收
     * @param oid 订单号
     * @return 执行结果
     */
    boolean setStateTake(int oid );

    /**
     * 查询当前登录用户的订单
     * @param user 当前登录用户
     * @return
     */
    List<OrderForm> selectOrderByUser(User user);

    /**
     * 查询指定状态的订单
     * @param state 订单状态
     * @return 订单数据
     */
    List<OrderForm> selectOrderByState(int state);

    /**
     * 查询所有订单
     * @return 订单数据
     */
    List<OrderForm> selectAllOrder();
}
