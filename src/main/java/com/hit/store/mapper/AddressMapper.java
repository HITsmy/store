package com.hit.store.mapper;

import com.hit.store.entity.Address;

import java.util.List;

/**
 * 对应收货地址类的mapper层
 */
public interface AddressMapper {
    /**
     *
     * @param address 收货地址
     * @return 返回收影响行数
     */
    Integer insert(Address address);

    /**
     *
     * @param uid Uid
     * @return 返回查询到的行数
     */
    Integer countByUid(Integer uid);

    /**
     *
     * @param uid uid
     * @return 返回查询到的地址列表
     */
    List<Address> getAddressByUid(Integer uid);

    /**
     *
     * @param aid 地址的id
     * @return 返回查询到的地址信息
     */

    Address getByAid(Integer aid);

    /**
     *
     * @param aid 地址的id
     * @return 返回受影响的行数
     */

    Integer deleteByAid(Integer aid);
}
