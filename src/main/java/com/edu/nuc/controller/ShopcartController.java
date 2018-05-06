package com.edu.nuc.controller;

import com.edu.nuc.entity.*;
import com.edu.nuc.jpa.OrderFormProductJpa;
import com.edu.nuc.service.OrderFormService;
import com.edu.nuc.service.ShoppingCartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by macbookair on 2018/4/17.
 */
@Controller
@RequestMapping("/user")
public class ShopcartController {
    @Autowired
    ShoppingCartService shoppingCartService;
    Logger log = LoggerFactory.getLogger(ShopcartController.class);


    /**
     * 将一个产品添加到购物车;
     *
     * @param session
     * @param pid
     * @return
     */
    @RequestMapping("/insertshopcart")
    @ResponseBody
    public String insertToShopcart(HttpSession session, Integer pid) {
        User user = (User) session.getAttribute("user");
        shoppingCartService.insert(user, pid, 1);
        log.info("insertToShopcart");
        return "";
    }

    /**
     * 添加商品数量
     *
     * @param scid
     * @param newpnumb
     * @return
     */
    @RequestMapping("/addpnumb")
    @ResponseBody
    public String addPNumb(Integer scid, Integer newpnumb) {
        shoppingCartService.altercount(scid, newpnumb);
        log.info("addPNmub");
        return "";
    }

    /**
     * 减少商品数量
     *
     * @param scid
     * @param newpnumb
     * @return
     */
    @RequestMapping("/reducepnumb")
    @ResponseBody
    public String reducePNumb(Integer scid, Integer newpnumb) {
        shoppingCartService.altercount(scid, newpnumb);
        log.info("reducePNumb");
        return "";
    }

    @RequestMapping("/findallbyuid")
    @ResponseBody
    public ModelAndView findAllByUid(HttpSession session) {
        User user = (User)session.getAttribute("user");
        List<ShoppingCart> shopingcart = shoppingCartService.findByUid(user.getUid());
        ModelAndView modelAndView = new ModelAndView("shoppingcart");
        modelAndView.addObject("shopcart",shopingcart);
        log.info("findallbyuid查到该用户的购物车");
        Map<Integer,BigDecimal> sp = new LinkedHashMap <>();
        BigDecimal sum= new BigDecimal(0);
        BigDecimal sumcount = new BigDecimal(0);
        for (ShoppingCart sc :shopingcart){
            BigDecimal discountprice = sc.getProduct().getDiscountprice();
            if (discountprice==null){
                BigDecimal price = sc.getProduct().getPrice();
                BigDecimal count= new BigDecimal(Double.valueOf(sc.getCount()));
                log.info("count数量为:"+count);
                BigDecimal sprice =price.multiply(count);
                sp.put(sc.getScid(),sprice);
                sum = sum.add(sprice);
                sumcount = sumcount.add(count);
            }else {
                BigDecimal count = new BigDecimal(Double.valueOf(sc.getCount()));
                log.info("count数量为:" + count);
                log.info("discountprice为:" + discountprice);
                BigDecimal sprice = discountprice.multiply(count);
                sp.put(sc.getScid(),sprice);
                sum = sum.add(sprice);
                sumcount = sumcount.add(count);
            }
        }
        log.info("sum:总价为"+sum);
        modelAndView.addObject("sp",sp);
        modelAndView.addObject("sumcount",sumcount);
        modelAndView.addObject("sum",sum);
        return modelAndView;
    }

    @RequestMapping("/delshopcartproduct")
    @ResponseBody
    public void deleteShopcartProduct(Integer scid){
        log.info("delete购物车中的产品"+scid);
        shoppingCartService.del(scid);
    }

}
