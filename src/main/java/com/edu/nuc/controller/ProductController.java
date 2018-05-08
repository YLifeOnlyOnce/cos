package com.edu.nuc.controller;

import com.edu.nuc.MyWebAppConfigurer;
import com.edu.nuc.entity.Product;
import com.edu.nuc.entity.User;
import com.edu.nuc.jpa.UserJPA;
import com.edu.nuc.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by macbookair on 2018/4/10.
 */
@Controller
@RequestMapping("/user")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    UserJPA userJPA;
    @Autowired
    MyWebAppConfigurer webAppConfigurer;
    Logger log = LoggerFactory.getLogger(ProductController.class);

    /**
     * 查询所有商品
     *
     * @return
     */
    @RequestMapping("/findallproduct")
    public ModelAndView findAllProduct(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("productview");
        List<Product> productList = productService.findAll();
        User user = (User) session.getAttribute("user");
        user = userJPA.getOne(user.getUid());
        session.setAttribute("user",user);
        modelAndView.addObject("productlist", productList);
        log.info("查到所有商品");
        return modelAndView;
    }

    @RequestMapping("/insertproduct")
    public ModelAndView putproduct() {
        return new ModelAndView("putproduct");
    }

    /**
     * 添加商品
     *
     * @param product
     * @param file
     * @return
     */
    @RequestMapping("/putproduct")
    public ModelAndView putProduct(Product product, MultipartFile file) {

        ModelAndView modelAndView = new ModelAndView("redirect:/user/findallproduct");
        String filename = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        log.info("filename:" + filename);
        String newfilename = UUID.randomUUID() + filename;
        String replace = newfilename.replace("-", "");

        File serverfile = new File(webAppConfigurer.picsPath() + replace);
        try {
            file.transferTo(serverfile);

        } catch (IOException e) {
            e.printStackTrace();
        }
        product.setDiscountstatus(product.notdiscount);
        product.setPimg("cospic" + "/" + replace);
        productService.putProduct(product);
        return modelAndView;

    }

    @RequestMapping("deleteproduct")
    @ResponseBody
    public Map<String, String> delete(Integer pid) {
        Map<String, String> map = new HashMap<>();
        boolean b = productService.soldOutProduct(pid);
        if (b) {
            map.put("code", "1");
        } else {
            map.put("code", "0");
        }
        return map;
    }

    @RequestMapping("tranfer")
    public ModelAndView ptp(Integer pid){
        Product pByPid = productService.findPByPid(pid);
        ModelAndView modelAndView = new ModelAndView("updateproduct");
        modelAndView.addObject("product",pByPid);
        return modelAndView;
    }

    @RequestMapping("updateproduct")
    public ModelAndView updateproduct(Product product){
        Product findedproduct = new Product();
        findedproduct=productService.findPByPid(product.getPid());
        findedproduct.setPname(product.getPname());
        findedproduct.setPrice(product.getPrice());
        findedproduct.setDiscountprice(product.getDiscountprice());
        findedproduct.setDescription(product.getDescription());
        productService.putProduct(findedproduct);
        return new ModelAndView("redirect:/user/findallproduct");
    }
}
