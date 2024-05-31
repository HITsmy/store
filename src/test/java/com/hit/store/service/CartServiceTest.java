package com.hit.store.service;

import com.hit.store.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CartServiceTest {
    @Autowired
    ICartService cartService;
    @Test
    public void addCart() {
        try {
            cartService.addCart(25, 10000018, 1, "腾博");
            System.out.println("success!");
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
    }
}
