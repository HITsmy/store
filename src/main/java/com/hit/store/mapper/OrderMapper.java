package com.hit.store.mapper;

import com.hit.store.entity.Order;
import com.hit.store.entity.OrderItem;

import java.util.List;

public interface OrderMapper {
    Integer insertOrder(Order order);
    Integer insertOrderItem(OrderItem orderItem);
    List<Integer> findOidsByUid(Integer uid);

    List<OrderItem> findOrderItemsByOid(List<Integer> oids);

    Integer deleteOrderItemsById(Integer id);
    OrderItem findOrderItemById(Integer id);

    Order findOrderByOid(Integer Oid);

    Integer updateStatusByOid(Order order);
}
