package com.hit.store.mapper;

import com.hit.store.entity.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressMapperTest {
    @Autowired
    AddressMapper addressMapper;
    @Test
    public void insert() {
        Address address = new Address();
        address.setAid(1);
        address.setPhone("18245622155");
        address.setUid(25);
        addressMapper.insert(address);
        System.out.println("success!");
    }
    @Test
    public void countByUid() {
        Integer uid = 25;
        Integer result = addressMapper.countByUid(uid);
        System.out.println(result);
    }

    @Test
    public void getAddressByUid() {
        Integer uid = 25;
        List<Address> addressList = addressMapper.getAddressByUid(uid);
        System.out.println("success!");
        System.out.println(addressList.get(2).getPhone());
    }

    @Test
    public void deleteByAid() {
        addressMapper.deleteByAid(24);
        addressMapper.deleteByAid(25);
        addressMapper.deleteByAid(26);
        addressMapper.deleteByAid(27);
        System.out.println("success!");
    }

}
