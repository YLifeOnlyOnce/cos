package com.edu.nuc.controller;

import com.edu.nuc.entity.User;
import com.edu.nuc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 用户控制器
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/userlist")
    public ModelAndView userList() {
        ModelAndView modelAndView = new ModelAndView("adminuser");
        List<User> all = userService.findAll();
        modelAndView.addObject("users", all);
        return modelAndView;
    }
}
