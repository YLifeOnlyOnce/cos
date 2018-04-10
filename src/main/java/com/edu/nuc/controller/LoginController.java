package com.edu.nuc.controller;

import com.edu.nuc.entity.User;
import com.edu.nuc.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by macbookair on 2018/4/9.
 */
@Controller
@RequestMapping
public class LoginController {
    @Autowired
    UserService userService;
    Logger log = LoggerFactory.getLogger(LoginController.class);
    @RequestMapping("/{page}")
    public String nnnnn(@PathVariable String page){
        log.info("访问"+page);
        return page;
    }


    @RequestMapping("/doLogin")
    public ModelAndView login(User user, HttpSession session){

        ModelAndView modelAndView = new ModelAndView("index");

        User user1 = userService.findByUsernameAPassword(user);
        if (user1==null){
            modelAndView.setViewName("login");
            modelAndView.addObject("loginerror","用户名或密码错误");
        }else {

            session.setAttribute("user",user);
        }

        return modelAndView;
    }

}


