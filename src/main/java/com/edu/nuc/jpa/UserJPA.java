package com.edu.nuc.jpa;

import com.edu.nuc.entity.User;
import com.sun.xml.internal.bind.v2.model.core.ID;

/**
 * Created by macbookair on 2018/4/9.
 */
public interface UserJPA extends BaseJPA<User,Integer> {

    User findByUsernameAndPassword(String username,String password);

}
