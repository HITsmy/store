package com.hit.store.mapper;

import com.hit.store.entity.Cart;
import com.hit.store.vo.CartVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CartMapperTest {
    @Autowired
    CartMapper cartMapper;
    @Test
    public void insert() {
        Cart cart = new Cart();
        cart.setUid(25);
        cart.setPid(10000017);
        cart.setNum(1);
        cart.setPrice(10000L);
        cart.setCreatedTime(new Date());
        cart.setCreateUser("宋明烨");
        cart.setModifiedUser("宋明烨");
        cart.setModifiedTime(new Date());
        cartMapper.insert(cart);
        System.out.println("success!");
    }
    @Test
    public void findByUidAndPid() {
        Cart cart= cartMapper.findByUidAndPid(25, 10000017);
        System.out.println(cart);
    }
    @Test
    public void updateByCid() {
        cartMapper.updateByCid(35, 4, "宋明烨", new Date());
        System.out.println("success!");
    }
    @Test
    public void findCartVOByUid() {
        List<CartVO> list = cartMapper.findCartVOByUid(25);
        System.out.println(list);
//        for (CartVO cartVO : list) {
//            System.out.println(cartVO);
//        }
    }
    @Test
    public void findByCid() {
        System.out.println(cartMapper.findByCid(35));
    }
    @Test
    public void deleteByCid() {
        cartMapper.deleteByCid(37);
        System.out.println("success!");
    }

    @Test
    public void findCartVOBycids() {
        Integer[] cids = {35, 36, 40};
        System.out.println(cartMapper.findCartVOByCids(cids));
    }
}
