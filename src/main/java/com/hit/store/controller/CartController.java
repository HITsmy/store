package com.hit.store.controller;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.hit.store.service.ICartService;
import com.hit.store.util.JsonResult;
import com.hit.store.vo.CartVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("carts")
public class CartController extends BaseController {
    @Autowired
    ICartService cartService;
    @RequestMapping("addToCart")
    public JsonResult<Void> addCart(Integer pid, Integer amount, HttpSession session) {
        JsonResult<Void> result = new JsonResult<>();
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        cartService.addCart(uid, pid, amount, username);
        result.setState(OK);
        return result;
    }
    @RequestMapping("/")
    public JsonResult<List<CartVO>> getCartVO(HttpSession session) {
        JsonResult<List<CartVO>> result = new JsonResult<>();
        Integer uid = getUidFromSession(session);
        List<CartVO> data = cartService.getCartVOByUid(uid);
        result.setState(OK);
        result.setData(data);
        return result;
    }
    @RequestMapping("{cid}/addNum")
    public JsonResult<Integer> addNum(@PathVariable("cid") Integer cid, HttpSession session) {
        JsonResult<Integer> result = new JsonResult<>();
        String username = getUsernameFromSession(session);
        Integer num = cartService.addCartNum(cid, username);
        result.setState(OK);
        result.setData(num);
        return result;
    }
    @RequestMapping("{cid}/reduceNum")
    public JsonResult<Integer> reduceNum(@PathVariable("cid") Integer cid, HttpSession session) {
        JsonResult<Integer> result = new JsonResult<>();
        String username = getUsernameFromSession(session);
        Integer num = cartService.reduceCartNum(cid, username);
        result.setState(OK);
        result.setData(num);
        return result;
    }
    @RequestMapping("{cid}/delete")
    public JsonResult<Void> deleteCart(@PathVariable("cid") Integer cid) {
        JsonResult<Void> result = new JsonResult<>();
        cartService.deleteCart(cid);
        result.setState(OK);
        return result;
    }

    @RequestMapping("list")
    public JsonResult<List<CartVO>> getCartVOByCids(Integer[] cids, HttpSession session) {
        JsonResult<List<CartVO>> result = new JsonResult<>();
        List<CartVO> data = cartService.getCartVOByCids(cids, getUidFromSession(session));
        result.setState(OK);
        result.setData(data);
        return result;
    }
}
