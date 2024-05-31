package com.hit.store.service.impl;

import com.hit.store.entity.Address;
import com.hit.store.mapper.AddressMapper;
import com.hit.store.service.IAddressService;
import com.hit.store.service.IDistrictService;
import com.hit.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AddressServiceImpl implements IAddressService {
    @Autowired
    AddressMapper addressMapper;

    @Autowired
    IDistrictService districtService;

    /**
     *
     * @param address 地址信息，需要补全日志信息
     */
    @Override
    public void addNewAddress(Integer uid, String username, Address address) {
        Integer count = addressMapper.countByUid(uid);
        if(count == 20) {
            throw new AddressCountException("地址数量不得超过20");
        }
        // 补全省市区数据
        address.setProvinceName(districtService.getNameByCode(address.getProvinceCode()));
        address.setCityName(districtService.getNameByCode(address.getCityCode()));
        address.setAreaName(districtService.getNameByCode(address.getAreaCode()));

        // 补全日志内容
        Date date = new Date();
        address.setUid(uid);
        address.setCreateUser(username);
        address.setCreatedTime(date);
        address.setModifiedUser(username);
        address.setModifiedTime(date);
        // 如果是第一次创建则设置为默认地址
        if(count == 0) {
            address.setIsDefault(1);
        }
        Integer rows = addressMapper.insert(address);
        if(rows == 0) {
            throw new InsertException("插入数据时出现未知错误");
        }
    }

    /**
     *
     * @param uid uid
     * @return 返回查询到的地址列表
     */
    @Override
    public List<Address> getAddress(Integer uid) {
        return addressMapper.getAddressByUid(uid);
    }

    @Override
    public Address getByAid(Integer aid) {
        Address address = addressMapper.getByAid(aid);
        if (address == null)
            throw new AddressNotFoundException("收货地址不存在");

        // 去除冗余信息
        address.setCreateUser(null);
        address.setCreatedTime(null);
        address.setModifiedUser(null);
        address.setModifiedTime(null);
        address.setAreaCode(null);
        address.setCityCode(null);
        address.setProvinceCode(null);

        return address;
    }

    @Override
    public void deleteByAid(Integer aid) {
        Address address = addressMapper.getByAid(aid);
        if (address == null)
            throw new AddressNotFoundException("该地址未找到");
        Integer result = addressMapper.deleteByAid(aid);
        if (result != 1)
            throw new UpdateException("删除数据时出现未知错误");
    }


}
