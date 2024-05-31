package com.hit.store.service;

import com.hit.store.vo.CartVO;


import java.util.List;

public interface ICartService {
    void addCart(Integer uid, Integer pid, Integer amount, String username);
    List<CartVO> getCartVOByUid(Integer uid);

    Integer addCartNum(Integer cid, String username);
    Integer reduceCartNum(Integer cid, String username);

    void deleteCart(Integer cid);

    List<CartVO> getCartVOByCids(Integer[] cids, Integer uid);
}
