package com.edu.nuc.controller;

import com.edu.nuc.entity.User;
import com.edu.nuc.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;

/**
 * Created by macbookair on 2018/4/10.
 */

@Controller
@RequestMapping
public class RegistController {
    @Autowired
    UserService userService;
    Logger log = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("/regist")
    public ModelAndView regist(User user) {
        user.setPower(user.userpower);
        user.setBalance(new BigDecimal(0));
        ModelAndView modelAndView = new ModelAndView("/login");
        modelAndView.addObject("user",user);
        User user1 = userService.regist(user);
        return modelAndView;
    }
}
