package com.houshl.mall.product.controller;

import com.houshl.mall.product.model.Product;
import com.houshl.mall.product.response.ObjectResponse;
import com.houshl.mall.product.response.ResponseUtils;
import com.houshl.mall.product.service.ProductService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by houshuanglong on 2018/7/13.
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @RequestMapping("/save")
    public ObjectResponse save(Product product) throws Exception {
        Product res = productService.save(product);
        return ResponseUtils.ok(res);
    }

    @RequestMapping("/{id}")
    public ObjectResponse find(@PathVariable("id") Long id) throws Exception {
        Product res = productService.find(id);
        return ResponseUtils.ok(res);
    }

    @RequestMapping("/list")
    public ObjectResponse list() throws Exception {
        List<Product> productList = productService.list();
        return ResponseUtils.ok(productList);
    }
}
