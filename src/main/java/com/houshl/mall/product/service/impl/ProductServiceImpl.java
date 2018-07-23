package com.houshl.mall.product.service.impl;

import com.houshl.mall.product.common.CommonUtils;
import com.houshl.mall.product.model.Product;
import com.houshl.mall.product.repository.ProductRepository;
import com.houshl.mall.product.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * Created by houshuanglong on 2018/7/23.
 */
@Service
@CacheConfig(cacheNames = "product")
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CachePut(key = "\"product:\"+#product.id")
    public Product save(Product product) throws Exception {
        Product res;
        if (product.getId() != null) {
            Product productTmp = productRepository.findOne(product.getId());
            BeanUtils.copyProperties(product, productTmp, CommonUtils.getNullPropertyNames(product));
            res = productRepository.save(productTmp);
        } else {
            product.setCreateTime(System.currentTimeMillis());
            res = productRepository.save(product);
        }

        return res;
    }

    @Override
    @Cacheable(key = "\"product:\"+#id")
    public Product find(Long id) throws Exception {
        Product product = productRepository.findOne(id);
        return product;
    }
}