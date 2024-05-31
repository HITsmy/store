package com.hit.store.mapper;

import com.hit.store.entity.District;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DistrictMapperTest {
    @Autowired
    DistrictMapper districtMapper;
    @Test
    public void getByParent() {
        List<District> list = districtMapper.getByParent("110100");
        System.out.println(list);
    }

    @Test
    public void findNameByCode() {
        String name = districtMapper.findNameByCode("130121");
        System.out.println(name);
    }
}
