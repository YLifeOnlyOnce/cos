package com.edu.nuc.service;

import com.edu.nuc.entity.ShoppingCart;

import javax.servlet.http.HttpSession;
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
     * @param session 当前session
     * @param pid 商品id
     * @param conut 商品数量
     * @return 执行结果
     */
    boolean insert(HttpSession session , Integer pid , Integer conut);


    /**
     * 删除购物车记录
     * @param scid 购物车记录id
     * @return 执行结果
     */
    boolean del(Integer scid);

    /**
     * 修改购物车商品数量
     * @param scid 购物车商品id
     * @param newConut 新的商品数量
     * @return 执行结果
     */
    boolean alterConut(Integer scid , Integer newConut);
}
