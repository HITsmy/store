package com.hit.store.mapper;

import com.hit.store.entity.Product;

import java.util.List;

public interface ProductMapper {
    /**
     *  查询商品列表
     * @return 返回商品列表
     */
    List<Product> findAll();

    /**
     *  根据商品id查询单个商品
     * @return
     */
    Product findById(Integer id);
}
