package com.hit.store.controller;

import com.hit.store.entity.Product;
import com.hit.store.service.IProductService;
import com.hit.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController extends BaseController{
    @Autowired
    IProductService productService;

    @RequestMapping("details")
    public JsonResult<List<Product>> getAll() {
        JsonResult<List<Product>> result = new JsonResult<>();
        List<Product> data = productService.findAll();
        result.setState(OK);
        result.setData(data);
        return result;
    }
    @RequestMapping ("{id}/details")
    public JsonResult<Product> getById(@PathVariable("id") Integer id) {
        JsonResult<Product> result = new JsonResult<>();
        Product data = productService.findById(id);
        result.setState(200);
        result.setData(data);
        return result;
    }

}
