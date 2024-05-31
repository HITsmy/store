package com.hit.store.mapper;

import com.hit.store.entity.Cart;
import com.hit.store.vo.CartVO;

import java.util.Date;
import java.util.List;

public interface CartMapper {

    /**
     *
     * @param cart 购物车
     * @return 返回受影响的行数
     */
    Integer insert(Cart cart);

    /**
     *  根据uid和pid查找购物车信息
     * @param uid 对应的用户id
     * @param pid 对应的商品id
     * @return 返回找到的购物车信息
     */
    Cart findByUidAndPid(Integer uid, Integer pid);

    /**
     *  根据cid修改购物车中商品的数量
     * @param cid 购物车id
     * @param num 要修改的数量
     * @param modified_user
     * @param modified_time
     * @return 返回受影响的行数
     */
    Integer updateByCid(Integer cid, Integer num, String modified_user, Date modified_time);

    /**
     *
     * @param uid 用户id
     * @return 返回cartVO列表
     */
    List<CartVO> findCartVOByUid(Integer uid);

    Cart findByCid(Integer cid);

    Integer deleteByCid(Integer cid);

    List<CartVO> findCartVOByCids(Integer[] cids);
}
