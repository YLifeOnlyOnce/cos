package com.edu.nuc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;

@Controller
@RequestMapping
public class TestController {
    @RequestMapping("/test")
    public ModelAndView test() {
        ModelAndView a = new ModelAndView("a");
        ArrayList<String> objects = new ArrayList<>();
        objects.add("a");
        objects.add("b");
        a.addObject("lists", objects);
        return a;
    }
}
