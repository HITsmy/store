package com.hit.store.mapper;

import com.hit.store.entity.District;

import java.util.List;

public interface DistrictMapper {
    /**
     *  根据父code获取区域
     * @param parent
     * @return 返回父code为parent的所有区域
     */
    List<District> getByParent(String parent);

    /**
     *  根据code查询该区域的名字
     * @param code
     * @return
     */
    String findNameByCode(String code);
}
