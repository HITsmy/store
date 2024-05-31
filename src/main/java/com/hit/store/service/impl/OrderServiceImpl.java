package com.hit.store.service.impl;

import com.hit.store.entity.Address;
import com.hit.store.entity.Order;
import com.hit.store.entity.OrderItem;
import com.hit.store.mapper.OrderMapper;
import com.hit.store.service.IAddressService;
import com.hit.store.service.ICartService;
import com.hit.store.service.IOrderService;
import com.hit.store.service.ex.InsertException;
import com.hit.store.service.ex.OrderNotFoundException;
import com.hit.store.service.ex.UpdateException;
import com.hit.store.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 *  订单的Service类
 */
@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    OrderMapper orderMapper;
    @Autowired
    IAddressService addressService;

    @Autowired
    ICartService cartService;

    /**
     *  新增订单的方法
     * @param uid 用户id
     * @param aid 收货地址id
     * @param username 用户名
     * @param cids 根据购物车创建订单，购物车cid列表
     * @return 订单内容
     */
    @Override
    public Order addOrder(Integer uid, Integer aid, String username, Integer[] cids) {
        Order order = new Order();

        // 获取到地址信息
        Address address = addressService.getByAid(aid);

        // 根据购物车列表计算订单总金额
        Long totalPrice = 0L;
        List<CartVO> cartVOList = cartService.getCartVOByCids(cids, uid);
        for (CartVO cartVO : cartVOList) {
            totalPrice += cartVO.getPrice() * cartVO.getNum();
        }

        // 将order补充
        order.setUid(uid);
        order.setRecvName(address.getName());
        order.setRecvPhone(address.getPhone());
        order.setRecvProvince(address.getProvinceName());
        order.setRecvCity(address.getCityName());
        order.setRecvArea(address.getAreaName());
        order.setRecvAddress(address.getAddress());
        order.setTotalPrice(totalPrice);
        order.setOrderTime(new Date());
        order.setStatus(0);
        order.setCreateUser(username);
        order.setCreatedTime(new Date());
        order.setModifiedTime(new Date());
        order.setModifiedUser(username);

        // 将order插入到数据库
        Integer rows = orderMapper.insertOrder(order);
        if (rows != 1)
            throw new UpdateException("插入数据时出现未知错误");

        // 插入orderItem项
        for (CartVO cartVO : cartVOList) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOid(order.getOid());
            orderItem.setPid(cartVO.getPid());
            orderItem.setNum(cartVO.getNum());
            orderItem.setTitle(cartVO.getTitle());
            orderItem.setPrice(cartVO.getRealPrice());
            orderItem.setImage(cartVO.getImage());
            orderItem.setCreatedTime(new Date());
            orderItem.setCreateUser(username);
            orderItem.setModifiedUser(username);
            orderItem.setModifiedTime(new Date());
            rows = orderMapper.insertOrderItem(orderItem);
            if (rows != 1)
                throw new InsertException("插入数据时出现未知异常");
        }
        return order;
    }

    /**
     *  根据uid获取该用户的所有订单item
     * @param uid uid
     * @return orderItem列表
     */
    @Override
    public List<OrderItem> getOrderItemsByUid(Integer uid) {
        List<Integer> oids = orderMapper.findOidsByUid(uid);
        List<OrderItem> orderItemList = orderMapper.findOrderItemsByOid(oids);
        return orderItemList;
    }

    @Override
    public void deleteOrderItemsById(Integer id) {
        OrderItem orderItem = orderMapper.findOrderItemById(id);
        if (orderItem == null)
            throw new OrderNotFoundException("未找到该订单信息");
        Integer rows = orderMapper.deleteOrderItemsById(id);
        if (rows != 1)
            throw new UpdateException("更新数据库时出现未知错误");
    }
}
