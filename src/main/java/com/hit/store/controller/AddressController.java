package com.hit.store.controller;

import com.hit.store.entity.Address;
import com.hit.store.service.IAddressService;
import com.hit.store.util.JsonResult;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("addresses")

public class AddressController extends BaseController{
    @Autowired
    IAddressService addressService;
    @RequestMapping("addNewAddress")
    public JsonResult<Void> addNewAddress(Address address, HttpSession session) {
        JsonResult<Void> result = new JsonResult<>();
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        addressService.addNewAddress(uid, username, address);
        result.setState(OK);
        return result;
    }
    @RequestMapping("/")
    public JsonResult<List<Address>> getAddressList(HttpSession session) {
        JsonResult<List<Address>> result = new JsonResult<>();
        Integer uid = getUidFromSession(session);
        result.setData(addressService.getAddress(uid));
        result.setState(200);
        return result;
    }
    @RequestMapping("/{aid}/delete")
    public JsonResult<Void> deleteAddress(@PathVariable("aid") Integer aid) {
        JsonResult<Void> result = new JsonResult<>();
        addressService.deleteByAid(aid);
        result.setState(OK);
        return result;
    }

}
