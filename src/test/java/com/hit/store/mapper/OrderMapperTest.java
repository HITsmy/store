package com.hit.store.mapper;

import com.hit.store.entity.Order;
import com.hit.store.entity.OrderItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderMapperTest {
    @Autowired
    OrderMapper orderMapper;
    @Test
    public void insertOrder() {
        Order order = new Order();
        order.setUid(25);
        order.setRecvName("滕博");
        order.setOrderTime(new Date());
        orderMapper.insertOrder(order);
        System.out.println("success!");
    }

    @Test
    public void findOidsByUid() {
        Integer uid = 25;
        List<Integer> oids = orderMapper.findOidsByUid(uid);
        System.out.println(oids);
    }
    @Test
    public void findOrderItemsByOids() {
        List<Integer> oids = new ArrayList<>();
        oids.add(23);
        oids.add(24);
        oids.add(25);
        List<OrderItem> result = orderMapper.findOrderItemsByOid(oids);
        System.out.println(result);
    }

}
