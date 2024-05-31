package com.hit.store.controller;

import com.hit.store.entity.District;
import com.hit.store.service.IDistrictService;
import com.hit.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("districts")
public class DistrictController extends BaseController{
    @Autowired
    IDistrictService districtService;
    @RequestMapping("/")
    public JsonResult<List<District>> getByParent(String parent) {
        JsonResult<List<District>> result = new JsonResult<>();
        List<District> data = districtService.getByParent(parent);
        result.setState(OK);
        result.setData(data);
        return result;
    }
}
