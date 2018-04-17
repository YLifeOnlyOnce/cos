package com.edu.nuc.controller;

import com.edu.nuc.entity.User;
import com.edu.nuc.jpa.UserJPA;
import com.edu.nuc.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by macbookair on 2018/4/14.
 */
@Controller
@RequestMapping("/user")
public class UpdateController {
    Logger log = LoggerFactory.getLogger(UpdateController.class);
    @Autowired
    UserService userService;

    /**
     * 修改密码
     * @param user
     * @return
     */
    @RequestMapping("/update")
    public ModelAndView update(User user) {
        int i=userService.update(user);
        log.info("updatestatus:"+i);
        if (i!=0) {
            log.info("修改成功");
           return new ModelAndView("redirect:/login");
        }
        else log.info("修改失败,用户名不存在");

        ModelAndView modelAndView = new ModelAndView("/userinfochange");
        modelAndView.addObject("updateerror","用户名不存在，修改失败");
        return modelAndView;
    }

}
