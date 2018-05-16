package com.edu.nuc.controller;

import com.edu.nuc.entity.Banner;
import com.edu.nuc.entity.Product;
import com.edu.nuc.entity.ProductType;
import com.edu.nuc.entity.User;
import com.edu.nuc.jpa.BannerJPA;
import com.edu.nuc.jpa.ProductTypeJPA;
import com.edu.nuc.jpa.UserJPA;
import com.edu.nuc.service.ProductService;
import com.edu.nuc.service.ProductTypeService;
import com.edu.nuc.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by macbookair on 2018/4/9.
 */
@Controller
@RequestMapping
public class LoginController {
    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;
    @Autowired
    BannerJPA bannerJPA;
    @Autowired
    ProductTypeService productTypeService;
    Logger log = LoggerFactory.getLogger(LoginController.class);
    @RequestMapping("/{page}")
    public String nnnnn(@PathVariable String page){
        log.info("访问"+page);
        return page;
    }

    @RequestMapping("/")
    public String index(){
        log.info("访问首页");
        return "redirect:/index";
    }


    /**
     * 登陆
     * @param user
     * @param session
     * @return
     */
    @RequestMapping("/doLogin")
    public ModelAndView login(User user, HttpSession session){

        ModelAndView modelAndView = new ModelAndView("index");

        User user1 = userService.findByUsernameAPassword(user);
        if (user1==null){
            modelAndView.setViewName("login");
            modelAndView.addObject("loginerror","用户名或密码错误");
        }else {
            log.info("登录成功");
            session.setAttribute("user",user1);
            return new ModelAndView("redirect:/user/findallproduct");
        }

        return modelAndView;
    }

    @RequestMapping("/index")
    public ModelAndView findAllProduct(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("productviewindex");
        List<Product> productList = productService.findAll();
        modelAndView.addObject("productlist", productList);
        List<Banner> all = bannerJPA.findAll();
        modelAndView.addObject("banners", all);
        List<ProductType> all1 = productTypeService.findAll();
        modelAndView.addObject("productTypes", all1);
        log.info("查到所有商品");
        return modelAndView;
    }

    @RequestMapping("/findProductByType")
    public ModelAndView findProductByType(ProductType productType,HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("productviewindex");
        List<Product> productList = productService.findByProductType(productType);
        User user = (User) session.getAttribute("user");
        modelAndView.addObject("productlist", productList);
        List<Banner> all = bannerJPA.findAll();
        modelAndView.addObject("banners", all);
        List<ProductType> all1 = productTypeService.findAll();
        modelAndView.addObject("productTypes", all1);
        modelAndView.addObject("nowptid", productType.getPtid());
        return modelAndView;
    }

}


