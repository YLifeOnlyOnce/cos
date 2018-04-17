package com.edu.nuc.controller;

import com.edu.nuc.entity.Product;
import com.edu.nuc.entity.User;
import com.edu.nuc.service.ShoppingCartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by macbookair on 2018/4/17.
 */
@Controller
@RequestMapping("/user")
public class ShopcartController {
    @Autowired
    ShoppingCartService shoppingCartService;
    Logger log = LoggerFactory.getLogger(ShopcartController.class);

    @RequestMapping("/insertshopcart")
    @ResponseBody
    public String insertToShopcart(Product product, HttpSession session, Integer pid){
        User user = (User)session.getAttribute("user");
        shoppingCartService.insert(user,pid,1);
        log.info("insertToShopcart");
        return "";
    }
}
