package com.hit.store.service.impl;

import com.hit.store.entity.Order;
import com.hit.store.mapper.OrderMapper;
import com.hit.store.service.IPayService;
import com.hit.store.service.ex.OrderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PayServiceImpl implements IPayService {
    @Autowired
    OrderMapper orderMapper;
    @Override
    public Long PayAOderByOid(Integer oid, String username) {
        Order order = orderMapper.findOrderByOid(oid);
        if (order == null)
            throw new OrderNotFoundException("订单信息未找到");
//        Date date = new Date();
//        order.setModifiedTime(date);
//        order.setModifiedUser(username);
//        order.setPayTime(date);
//        order.setStatus(1);
//        orderMapper.updateStatusByOid(order);
        return order.getTotalPrice();
    }
}
