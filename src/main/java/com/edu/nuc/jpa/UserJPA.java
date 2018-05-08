package com.edu.nuc.jpa;

import com.edu.nuc.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by macbookair on 2018/4/9.
 */
public interface UserJPA extends BaseJPA<User,Integer> {

    User findByUsernameAndPassword(String username,String password);
    User findByUsername(String username);

    @Modifying
    @Query("update User u set u.password= ?2 where u.username = ?1")
    int updatePasswordbyname(String username,String password);

}
