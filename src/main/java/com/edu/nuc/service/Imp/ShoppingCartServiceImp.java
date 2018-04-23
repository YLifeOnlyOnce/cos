package com.edu.nuc.service.Imp;

import com.edu.nuc.entity.Product;
import com.edu.nuc.entity.ShoppingCart;
import com.edu.nuc.entity.User;
import com.edu.nuc.jpa.ShoppingCartJpa;
import com.edu.nuc.service.ShoppingCartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ShoppingCartServiceImp implements ShoppingCartService {
    Logger log = LoggerFactory.getLogger(ShoppingCartServiceImp.class);
    @Autowired
    ShoppingCartJpa shoppingCartJpa;
    @Override
    public List<ShoppingCart> findByUid(Integer uid) {
        log.info("传来的"+uid);
        User user = new User();
        user.setUid(uid);
        return shoppingCartJpa.findByUser(user);
    }

    @Override
    public ShoppingCart insert(User user, Integer pid, Integer count) {
        Product product = new Product();
        product.setPid(pid);
        ShoppingCart byUserAndProducts = shoppingCartJpa.findByUserAndProduct(user, product);
        if (byUserAndProducts != null) {
            byUserAndProducts.setCount(byUserAndProducts.getCount() + 1);
            return shoppingCartJpa.save(byUserAndProducts);
        }
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setCount(count);
        shoppingCart.setUser(user);
        shoppingCart.setProduct(product);
        return shoppingCartJpa.save(shoppingCart);
    }

    @Override
    public boolean del(Integer scid) {
        shoppingCartJpa.deleteById(scid);
        return true;
    }

    @Override
    public boolean altercount(Integer scid, Integer newcount) {
        ShoppingCart shoppingCart = shoppingCartJpa.getOne(scid);
        if (shoppingCart == null) {
            return false;
        }
        shoppingCart.setCount(newcount);
        shoppingCartJpa.save(shoppingCart);
        return true;
    }
}
