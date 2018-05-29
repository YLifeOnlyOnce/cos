package com.edu.nuc.service.Imp;

import com.edu.nuc.entity.OrderForm;
import com.edu.nuc.entity.OrderFormProduct;
import com.edu.nuc.entity.Product;
import com.edu.nuc.entity.User;
import com.edu.nuc.jpa.*;
import com.edu.nuc.service.OrderFormService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
@Service
@Transactional(rollbackFor = Exception.class)
public class OrderFormServiceImp implements OrderFormService {
    Logger log = LoggerFactory.getLogger(OrderFormServiceImp.class);
    @Autowired
    OrderFormJpa orderFormJpa;
    @Autowired
    ProductJPA productJPA;
    @Autowired
    OrderFormProductJpa orderFormProductJpa;
    @Autowired
    UserJPA userJPA;
    @Autowired
    ShoppingCartJpa shoppingCartJpa;
    @Override
    public boolean insertOrder(OrderForm orderForm) {
        orderForm.setState(OrderForm.place);
        orderForm.setPlaceTime(new Date());
        List<OrderFormProduct> orderFormProducts = orderForm.getOrderFormProducts();
        BigDecimal zj = new BigDecimal(0);
        for (OrderFormProduct orderFormProduct : orderFormProducts) {
            //设置商品价格
            Integer pid = orderFormProduct.getProduct().getPid();
            Product one = productJPA.getOne(pid);
            double v = one.getDiscountprice().doubleValue();
            BigDecimal price = null;
            if (v == 0) {
                price = one.getPrice();
            } else {
                price = one.getDiscountprice();
            }

            orderFormProduct.setPrice(price);
            //计算总价
            zj = zj.add(price.multiply(new BigDecimal(orderFormProduct.getCount())));
            int count = orderFormProduct.getCount();
            one.setSalesnumb(one.getSalesnumb() + count);
            productJPA.save(one);
            orderFormProductJpa.save(orderFormProduct);
        }
        orderForm.setTotalPrices(zj);
        User user = orderForm.getUser();
        Integer uid = user.getUid();
        user = userJPA.getOne(uid);
        BigDecimal balance = user.getBalance();
        double v = balance.doubleValue();
        double v1 = zj.doubleValue();
        if (v1 > v) {
            log.warn("账户余额不足");
            return false;
        }
        user.setBalance(balance.subtract(zj));
        userJPA.save(user);
        orderFormJpa.save(orderForm);
        shoppingCartJpa.deleteAllByUser(user);
        return true;
    }


    @Override
    public boolean setStateSenfOf(int oid) {
        OrderForm one = orderFormJpa.getOne(oid);
        int state = one.getState();
        if (state == OrderForm.senfOf || state == OrderForm.take) {
            return false;
        }
        one.setState(OrderForm.senfOf);
        one.setSenfOfTime(new Date());
        orderFormJpa.save(one);
        return true;
    }

    @Override
    public boolean setStateTake(int oid) {
        OrderForm one = orderFormJpa.getOne(oid);
        int state = one.getState();
        if (state == OrderForm.place || state == OrderForm.take) {
            return false;
        }
        one.setState(OrderForm.take);
        one.setTakeTime(new Date());
        orderFormJpa.save(one);
        return true;
    }

    @Override
    public List<OrderForm> selectOrderByUser(User user) {
        return orderFormJpa.findByUserOrderByPlaceTimeDesc(user);
    }

    @Override
    public List<OrderForm> selectOrderByState(int state) {
        return orderFormJpa.findByStateOrderByPlaceTimeDesc(state);
    }

    @Override
    public List<OrderForm> selectAllOrder() {
        return orderFormJpa.findAllOrderByState();
    }
}
