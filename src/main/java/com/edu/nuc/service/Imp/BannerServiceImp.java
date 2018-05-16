package com.edu.nuc.service.Imp;

import com.edu.nuc.entity.Banner;
import com.edu.nuc.jpa.BannerJPA;
import com.edu.nuc.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BannerServiceImp implements BannerService {
    @Autowired
    BannerJPA bannerJPA;
    @Override
    public boolean insert(Banner banner) {
        Banner save = bannerJPA.save(banner);
        if (save == null) {
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Integer bid) {
        bannerJPA.deleteById(bid);
        return true;
    }

    @Override
    public List<Banner> selectALL() {
        List<Banner> all = bannerJPA.findAll();
        return all;
    }
}
