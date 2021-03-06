package com.edu.nuc.service;

import com.edu.nuc.entity.ShoppingCart;
import com.edu.nuc.entity.User;

import java.util.List;

/**
 * 购物车业务逻辑
 */
public interface ShoppingCartService {
    /**
     * 查询某个用户的购物车
     * @param uid 用户id
     * @return
     */
    List<ShoppingCart> findByUid(Integer uid);

    /**
     * 添加购物车记录
     * @param user 当前登录用户从session获得
     * @param pid 商品id
     * @param count 商品数量
     * @return 执行结果
     */
    ShoppingCart insert(User user, Integer pid , Integer count);


    /**
     * 删除购物车记录
     * @param scid 购物车记录id
     * @return 执行结果
     */
    boolean del(Integer scid);

    /**
     * 修改购物车商品数量
     * @param scid 购物车商品id
     * @param newcount 新的商品数量
     * @return 执行结果
     */
    boolean altercount(Integer scid , Integer newcount);
}
