package com.edu.nuc.service.Imp;

import com.edu.nuc.entity.User;
import com.edu.nuc.jpa.UserJPA;
import com.edu.nuc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by macbookair on 2018/4/9.
 */
@Service
@Transactional(rollbackFor = Exception.class )
public class UserServiceImp implements UserService{
    @Autowired
    UserJPA userJPA;
    @Override
    public User findByUsernameAPassword(User user) {

        return userJPA.findByUsernameAndPassword(user.getUsername(), user.getPassword());

    }

    @Override
    public User regist(User user) {

        userJPA.save(user);
        return user;
    }


    @Override
    public int update(User user) {

        int i=userJPA.updatePasswordbyname(user.getUsername(),user.getPassword());
        return i;
    }
}
