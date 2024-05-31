package com.hit.store.service;

import com.hit.store.entity.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();
    Product findById(Integer Id);
}
