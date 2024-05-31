package com.hit.store.service;

import com.hit.store.entity.District;

import java.util.List;

public interface IDistrictService {
    List<District> getByParent(String parent);
    String getNameByCode(String code);
}
