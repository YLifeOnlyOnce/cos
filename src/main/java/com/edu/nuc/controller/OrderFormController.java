package com.edu.nuc.controller;

import com.edu.nuc.entity.OrderForm;
import com.edu.nuc.entity.OrderFormProduct;
import com.edu.nuc.entity.User;
import com.edu.nuc.service.OrderFormService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @RequestMapping("/adminOrder")
    public ModelAndView adminOrder(HttpSession session) {
        ModelAndView order = new ModelAndView("adminorder");
        User user = (User) session.getAttribute("user");
        log.info("管理员查看订单，身份"+user.getPower());
        log.info("session取出的user" + user.getUid());
        List<OrderForm> orderForms;
        if (!user.getPower().equals(User.adminpower)) {
            log.info("身份错误");
            orderForms = orderFormService.selectOrderByUser(user);
        } else {
            log.info("身份正确");
            orderForms = orderFormService.selectAllOrder();
        }
        order.addObject("orderForms", orderForms);
        return order;
    }

    /**
     * 首页下单
     *
     * @return
     */
    @RequestMapping("/sxorder")
    @ResponseBody
    public Map<String, String> sxorder(@RequestBody OrderForm orderForm, HttpSession session) {
        Map<String, String> map = new HashMap<String, String>();
        log.info("首页下单：" + orderForm);
        User user = (User) session.getAttribute("user");
        orderForm.setUser(user);
        boolean b = orderFormService.insertOrder(orderForm);
        if (b) {
            map.put("code", "1");
        } else {
            map.put("code", "0");
        }
        return map;
    }

    /**
     * 确认订单
     * @return
     */
    @RequestMapping("/qrorder")
    @ResponseBody
    public Map<String,String> qrorder(Integer oid) {
        log.info("确认收货："+oid);
        boolean b = orderFormService.setStateTake(oid);
        log.info("确认收货结果"+b);
        HashMap<String, String> map = new HashMap<>();
        map.put("state", "1");
        return map;
    }

    /**
     * 发货
     * @return
     */
    @RequestMapping("/fhorder")
    @ResponseBody
    public Map<String,String> fhorder(Integer oid) {
        log.info("发货："+oid);
        boolean b = orderFormService.setStateSenfOf(oid);
        log.info("发货结果"+b);
        HashMap<String, String> map = new HashMap<>();
        map.put("state", "1");
        return map;
    }
}
