package com.hit.store.mapper;

import com.hit.store.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductMapperTest {
    @Autowired
    ProductMapper productMapper;
    @Test
    public void findAll() {
        List<Product> res = productMapper.findAll();
        System.out.println(res);
    }

    @Test
    public void findById() {
        Product product  = productMapper.findById(10000042);
        System.out.println(product);
    }
}
