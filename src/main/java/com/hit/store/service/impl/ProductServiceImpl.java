package com.hit.store.service.impl;

import com.hit.store.entity.Product;
import com.hit.store.mapper.ProductMapper;
import com.hit.store.service.IProductService;
import com.hit.store.service.ex.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    ProductMapper productMapper;
    /**
     *  获取商品列表
     * @return 返回商品列表
     */
    @Override
    public List<Product> findAll() {
        List<Product> list = productMapper.findAll();
        for (Product product : list) {
            product.setCreatedTime(null);
            product.setCreateUser(null);
            product.setStatus(null);
            product.setModifiedTime(null);
            product.setModifiedUser(null);
        }
        return list;
    }

    /**
     *  根据商品id获取单个商品的信息
     * @param Id
     * @return 返回单个商品
     */
    @Override
    public Product findById(Integer Id) {
        Product result = productMapper.findById(Id);
        if (result == null) {
            throw new ProductNotFoundException("商品未找到");
        }
        result.setCreateUser(null);
        result.setCreatedTime(null);
        result.setModifiedUser(null);
        result.setModifiedTime(null);
        return result;
    }
}
