package com.hit.store.service;

import com.hit.store.entity.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderServiceTest {
    @Autowired
    IOrderService orderService;
    @Test
    public void addOrder(){
        Integer[] cids = {35, 36};
        Order order = orderService.addOrder(25, 26, "滕博", cids);
        System.out.println(order);
    }
}
