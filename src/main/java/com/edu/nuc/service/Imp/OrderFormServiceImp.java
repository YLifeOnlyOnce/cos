package com.edu.nuc.service.Imp;

import com.edu.nuc.entity.OrderForm;
import com.edu.nuc.entity.OrderFormProduct;
import com.edu.nuc.entity.Product;
import com.edu.nuc.entity.User;
import com.edu.nuc.jpa.OrderFormJpa;
import com.edu.nuc.jpa.ProductJPA;
import com.edu.nuc.jpa.UserJPA;
import com.edu.nuc.service.OrderFormService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
@Service
public class OrderFormServiceImp implements OrderFormService {
    Logger log = LoggerFactory.getLogger(OrderFormServiceImp.class);
    @Autowired
    OrderFormJpa orderFormJpa;
    @Autowired
    ProductJPA productJPA;
    @Autowired
    UserJPA userJPA;
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
            orderFormProduct.setPrice(one.getPrice());
            //计算总价
            zj = zj.add(one.getPrice().multiply(new BigDecimal(orderFormProduct.getConut())));
        }
        orderForm.setTotalPrices(zj);
        User user = orderForm.getUser();
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
}
