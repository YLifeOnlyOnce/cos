package com.edu.nuc.controller;

import com.edu.nuc.entity.Banner;
import com.edu.nuc.entity.ProductType;
import com.edu.nuc.service.ProductTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class ProductTypeController {
    Logger log = LoggerFactory.getLogger(ProductTypeController.class);
    @Autowired
    ProductTypeService productTypeService;
    @RequestMapping("/productTypelist")
    public ModelAndView bannerList() {
        ModelAndView modelAndView = new ModelAndView("productType");
        List<ProductType> all = productTypeService.findAll();
        modelAndView.addObject("productTypes", all);
        return modelAndView;
    }
    @RequestMapping("/putProductType")
    public ModelAndView putProductType(ProductType productType) {
        ModelAndView modelAndView = new ModelAndView("redirect:/user/productTypelist");
        productTypeService.insert(productType);
        return modelAndView;
    }

    @RequestMapping("/putProductTypePage")
    public ModelAndView putProductTypePage(ProductType productType) {
        ModelAndView modelAndView = new ModelAndView("putProductType");
        return modelAndView;
    }


    @RequestMapping("/deleteProductType")
    @ResponseBody
    public Map<String, String> deleteProductType(Integer ptid) {
        Map<String , String> map = new HashMap<String, String>();
        boolean delete = productTypeService.delete(ptid);
        if (delete) {
            map.put("code", "1");
        } else {
            map.put("code", "0");
        }
        return map;
    }
}
