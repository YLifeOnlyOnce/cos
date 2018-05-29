package com.edu.nuc.controller;

import com.edu.nuc.entity.User;
import com.edu.nuc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @RequestMapping("/deleteUser")
    @ResponseBody
    public Map<String,String> deleteUser(Integer uid) {
        Map<String, String> map = new HashMap<>();
        userService.deleteByID(uid);
        map.put("code", "1");
        return map;
    }

}
