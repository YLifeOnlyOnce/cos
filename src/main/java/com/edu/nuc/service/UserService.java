package com.edu.nuc.service;


import com.edu.nuc.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @Author: hao
 * @Date: 2018/3/19 17:57
 * @Description: 操作用户的业务逻辑
 */
public interface UserService {

    User findByUsernameAPassword(User user);

    User regist(User user);

    int update(User user);

    /**
     * 充值
     * @param uid 用户id
     * @param money 金额
     * @return 结果
     */
    boolean topUpRecharge(Integer uid , BigDecimal money);

}
