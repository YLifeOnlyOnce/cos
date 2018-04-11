package com.edu.nuc.service.Imp;

import com.edu.nuc.entity.Product;
import com.edu.nuc.entity.ShoppingCart;
import com.edu.nuc.entity.User;
import com.edu.nuc.jpa.ShoppingCartJpa;
import com.edu.nuc.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ShoppingCartServiceImp implements ShoppingCartService {
    @Autowired
    ShoppingCartJpa shoppingCartJpa;
    @Override
    public List<ShoppingCart> findByUid(Integer uid) {
        User user = new User();
        user.setUid(uid);
        return shoppingCartJpa.findByUser(user);
    }

    @Override
    public boolean insert(HttpSession session, Integer pid, Integer conut) {
        User user = (User) session.getAttribute("user");
        Product product = new Product();
        product.setPid(pid);
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setConut(conut);
        shoppingCart.setUser(user);
        shoppingCart.setProducts(product);
        ShoppingCart save = shoppingCartJpa.save(shoppingCart);
        return save.getScid()!=null;
    }

    @Override
    public boolean del(Integer scid) {
        shoppingCartJpa.deleteById(scid);
        return true;
    }

    @Override
    public boolean alterConut(Integer scid, Integer newConut) {
        ShoppingCart shoppingCart = shoppingCartJpa.getOne(scid);
        if (shoppingCart == null) {
            return false;
        }
        shoppingCart.setConut(newConut);
        shoppingCartJpa.save(shoppingCart);
        return true;
    }
}
