package com.hit.store.controller;

import com.hit.store.entity.Order;
import com.hit.store.entity.OrderItem;
import com.hit.store.service.IOrderService;
import com.hit.store.util.JsonResult;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderController extends BaseController{
    @Autowired
    IOrderService orderService;
    @RequestMapping("createOrder")
    public JsonResult<Order> createOrder(Integer aid, Integer[] cids, HttpSession session) {
        JsonResult<Order> result = new JsonResult<>();
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        Order data = orderService.addOrder(uid, aid, username, cids);
        result.setData(data);
        result.setState(OK);

        return result;
    }
    @RequestMapping("/")
    public JsonResult<List<OrderItem>> getOrderItem(HttpSession session) {
        JsonResult<List<OrderItem>> result = new JsonResult<>();
        Integer uid = getUidFromSession(session);
        List<OrderItem> data = orderService.getOrderItemsByUid(uid);
        result.setState(OK);
        result.setData(data);
        return result;

    }

    @RequestMapping("/{id}/deleteOrderItem")
    public JsonResult<Void> deleteOrderItem(@PathVariable("id") Integer id) {
        JsonResult<Void> result = new JsonResult<>();
        orderService.deleteOrderItemsById(id);
        result.setState(OK);
        return result;
    }

}
