package com.hit.store.service;


import com.hit.store.entity.Order;
import com.hit.store.entity.OrderItem;

import java.util.List;

public interface IOrderService {
    Order addOrder(Integer uid, Integer aid, String username, Integer[] cids);
    List<OrderItem> getOrderItemsByUid(Integer uid);

    void deleteOrderItemsById(Integer id);

}
