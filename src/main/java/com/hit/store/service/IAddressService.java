package com.hit.store.service;

import com.hit.store.entity.Address;

import java.util.List;

public interface IAddressService {
    void addNewAddress(Integer uid, String username, Address address);
    List<Address> getAddress(Integer uid);
    Address getByAid(Integer aid);
    void deleteByAid(Integer aid);
}
