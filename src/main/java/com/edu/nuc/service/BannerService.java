package com.edu.nuc.service;

import com.edu.nuc.entity.Banner;

import java.util.List;

/**
 * 轮播图业务逻辑
 */
public interface BannerService {
    /**
     * 添加轮播图
     * @param banner 轮播图对象
     * @return 添加结果
     */
    boolean insert(Banner banner);

    /**
     * 删除轮播图
     * @param bid
     * @return
     */
    boolean delete(Integer bid);

    /**
     * 查询所有的轮播图
     * @return
     */
    List<Banner> selectALL();
}
