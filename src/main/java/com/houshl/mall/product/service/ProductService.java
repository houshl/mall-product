package com.houshl.mall.product.service;

import com.houshl.mall.product.model.Product;

import java.util.List;

/**
 * Created by houshuanglong on 2018/7/23.
 */
public interface ProductService {

    Product save(Product product) throws Exception;

    Product find(Long id) throws Exception;

    List<Product> list() throws Exception;

}
