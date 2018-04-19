package com.edu.nuc.controller;

import com.edu.nuc.entity.OrderForm;
import com.edu.nuc.entity.User;
import com.edu.nuc.service.OrderFormService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 用户订单控制器
 */
@Controller
@RequestMapping("/user")
public class OrderFormController {
    Logger log =  LoggerFactory.getLogger(OrderFormController.class);
    @Autowired
    OrderFormService orderFormService;

    @RequestMapping("/order")
    public ModelAndView order(HttpSession session) {
        ModelAndView order = new ModelAndView("order");
        User user = (User) session.getAttribute("user");
        log.info("session取出的user" + user.getUid());
        List<OrderForm> orderForms = orderFormService.selectOrderByUser(user);
        order.addObject("orderForms", orderForms);
        return order;
    }
}
