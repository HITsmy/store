package com.hit.store.service;

import com.hit.store.entity.Address;
import com.hit.store.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressServiceTest {
    @Autowired
    private IAddressService addressService;
    @Test
    public void addNewAddress() {
        Address address = new Address();
        address.setPhone("666666");
        try {
            addressService.addNewAddress(25, "管理员", address);
            System.out.println("success!");
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
    }
    @Test

    public void getAddressList() {
        Integer uid = 1000;
        List<Address> addressList = addressService.getAddress(uid);
        System.out.println(addressList);
        System.out.println("success!");
    }
}
