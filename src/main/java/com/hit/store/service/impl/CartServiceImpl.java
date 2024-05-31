package com.hit.store.service.impl;

import com.hit.store.entity.Cart;
import com.hit.store.mapper.CartMapper;
import com.hit.store.mapper.ProductMapper;
import com.hit.store.service.ICartService;
import com.hit.store.service.ex.CartNotFoundException;
import com.hit.store.service.ex.InsertException;
import com.hit.store.service.ex.UpdateException;
import com.hit.store.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CartServiceImpl implements ICartService {
    @Autowired
    CartMapper cartMapper;

    @Autowired
    ProductMapper productMapper;
    /**
     *  增加购物车函数
     * @param uid 用户id
     * @param pid   商品id
     * @param amount    购买商品数量
     * @param username  用户名称（用于日志）
     */
    @Override
    public void addCart(Integer uid, Integer pid, Integer amount, String username) {
        // 先查询该用户要添加的商品在购物车中是否已经存在
        Cart result = cartMapper.findByUidAndPid(uid, pid);
        Date date = new Date();
        if(result == null) {  // 说明不存在，则插入一个购物车
            // 补全cart中的数据
            Cart cart = new Cart();
            // 通过productMapper来获取商品价格
            Long price = productMapper.findById(pid).getPrice();
            cart.setUid(uid);
            cart.setPid(pid);
            cart.setPrice(price);
            cart.setNum(amount);
            cart.setCreateUser(username);
            cart.setCreatedTime(date);
            cart.setModifiedUser(username);
            cart.setModifiedTime(date);
            Integer rows = cartMapper.insert(cart);
            if(rows != 1)
                throw new InsertException("插入时出现未知异常");
        } else {    // 说明存在购物车，则更改购物车中的数据即可
            Integer rows = cartMapper.updateByCid(result.getCid(), amount+ result.getNum(),
                    username, date);
            if (rows != 1)
                throw new UpdateException("更新数据时出现未知异常");
        }


    }

    /**
     *
     * @param uid uid
     * @return 返回cartVO列表
     */
    @Override
    public List<CartVO> getCartVOByUid(Integer uid) {
        return cartMapper.findCartVOByUid(uid);
    }

    /**
     *
     * @param cid cid
     * @param username 更改者名称
     */
    @Override
    public Integer addCartNum(Integer cid, String username) {
        Cart result = cartMapper.findByCid(cid);
        if(result == null) {
            throw new CartNotFoundException("该购物车信息不存在");
        }
        Date date = new Date();
        Integer num = result.getNum();
        Integer rows = cartMapper.updateByCid(cid, num+1, username, date);
        if(rows != 1) {
            throw new UpdateException("更新数据时出现未知错误");
        }
        return num + 1;
    }

    @Override
    public Integer reduceCartNum(Integer cid, String username) {
        Cart result = cartMapper.findByCid(cid);
        if(result == null) {
            throw new CartNotFoundException("该购物车信息不存在");
        }
        Date date = new Date();
        Integer num = result.getNum();
        Integer rows = cartMapper.updateByCid(cid, num-1, username, date);
        if(rows != 1) {
            throw new UpdateException("更新数据时出现未知错误");
        }
        return num - 1;
    }

    @Override
    public void deleteCart(Integer cid) {
        Cart result = cartMapper.findByCid(cid);
        if(result == null) {
            throw new CartNotFoundException("该购物车信息不存在");
        }
        Integer rows = cartMapper.deleteByCid(cid);
        if(rows != 1) {
            throw new UpdateException("更新数据时出现未知错误");
        }
    }

    /**
     *
     * @param cids
     * @param uid
     * @return 返回cartVO类型
     */
    @Override
    public List<CartVO> getCartVOByCids(Integer[] cids, Integer uid) {
        List<CartVO> list = cartMapper.findCartVOByCids(cids);

        // 检查cart中的内容是否属于该用户
        for (CartVO cartVO : list) {
            if (cartVO.getUid() != uid)
                list.remove(cartVO);
        }
        return list;
    }

}
