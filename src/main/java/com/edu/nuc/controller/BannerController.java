package com.edu.nuc.controller;

import com.edu.nuc.MyWebAppConfigurer;
import com.edu.nuc.entity.Banner;
import com.edu.nuc.entity.Product;
import com.edu.nuc.jpa.ProductJPA;
import com.edu.nuc.service.BannerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class BannerController {
    @Autowired
    BannerService bannerService;
    @Autowired
    ProductJPA productJPA;
    Logger log = LoggerFactory.getLogger(BannerController.class);

    @RequestMapping("/bannerlist")
    public ModelAndView bannerList() {
        ModelAndView modelAndView = new ModelAndView("banner");
        List<Banner> banners = bannerService.selectALL();
        modelAndView.addObject("banners", banners);
        return modelAndView;
    }

    @RequestMapping("/putbanner")
    public ModelAndView putbanner() {
        ModelAndView modelAndView = new ModelAndView("putbanner");
        List<Product> all = productJPA.findAll();
        modelAndView.addObject("products", all);
        return modelAndView;
    }

    @RequestMapping("/doputbanner")
    public ModelAndView doPutBanner(Banner banner, MultipartFile file) {

        ModelAndView modelAndView = new ModelAndView("redirect:/user/bannerlist");
        String filename = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        log.info("filename:" + filename);
        String newfilename = UUID.randomUUID() + filename;
        String replace = newfilename.replace("-", "");

        File serverfile = new File(MyWebAppConfigurer.picsPath() + replace);
        try {
            file.transferTo(serverfile);

        } catch (IOException e) {
            e.printStackTrace();
        }
        banner.setBimg("cospic" + "/" + replace);
        bannerService.insert(banner);
        return modelAndView;

    }

    @RequestMapping("deleteBannner")
    @ResponseBody
    public Map<String, String> deleteBanner(Integer bid) {
        Map<String, String> map = new HashMap<>();
        bannerService.delete(bid);
        map.put("code", "1");
        return map;
    }


}
