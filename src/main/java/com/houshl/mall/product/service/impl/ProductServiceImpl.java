package com.houshl.mall.product.service.impl;

import com.houshl.mall.product.exception.PromptException;
import com.houshl.mall.product.model.Product;
import com.houshl.mall.product.repository.ProductRepository;
import com.houshl.mall.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by houshuanglong on 2018/7/23.
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Product save(Product product) throws Exception {
        product.setCreateTime(System.currentTimeMillis());
        Product res = productRepository.save(product);
        return res;
    }
}
