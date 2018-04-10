package com.edu.nuc.controller;

import com.edu.nuc.MyWebAppConfigurer;
import com.edu.nuc.entity.Product;
import com.edu.nuc.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * Created by macbookair on 2018/4/10.
 */
@Controller
@RequestMapping("/user")
public class ProductController  {
    @Autowired
    ProductService productService;
    @Autowired
    MyWebAppConfigurer webAppConfigurer;
    Logger log = LoggerFactory.getLogger(ProductController.class);

    @RequestMapping("/findallproduct")
    public ModelAndView findAllProduct(){
        ModelAndView modelAndView = new ModelAndView("index");
        List <Product> productList = productService.findAll();
        modelAndView.addObject("productlist",productList);
        log.info("查到所有商品");
        return modelAndView;
    }

    @RequestMapping("/putproduct")
    public ModelAndView putProduct(Product product,MultipartFile file){
        ModelAndView modelAndView = new ModelAndView("index");

        String filename=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        log.info("filename:"+filename);
        String newfilename = UUID.randomUUID()+filename;

        File serverfile = new File(webAppConfigurer.linux_path+newfilename);
        try {
            file.transferTo(serverfile);

        } catch (IOException e) {
            e.printStackTrace();
        }
        String replace = newfilename.replace("-", "");
        product.setPimg("cospics"+"/"+replace);
        productService.putProduct(product);
        return modelAndView;

    }
}
