package com.houshl.mall.product.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.houshl.mall.product.common.CommonUtils;
import com.houshl.mall.product.common.Constants;
import com.houshl.mall.product.model.Product;
import com.houshl.mall.product.repository.ProductRepository;
import com.houshl.mall.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 *
 * @author houshuanglong
 * @date 2018/7/23
 */
@Service
@CacheConfig(cacheNames = "product")
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    ObjectMapper mapper;

    final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            log.info("correlationData={}, ack={}, cause={}", correlationData, ack, cause);
        }
    };

    final RabbitTemplate.ReturnCallback returnCallback = new RabbitTemplate.ReturnCallback() {
        @Override
        public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
            log.error("message:{}, replyCode:{}, replyText:{}, exchange:{}, routingKey:{}",
                    new String(message.getBody()), replyCode, replyText, replyText);
        }
    };

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
            product.setCreateTime(new Date());
            res = productRepository.save(product);
        }
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnCallback(returnCallback);
        rabbitTemplate.convertAndSend(Constants.PRODUCT_EXCHANGE, "product:new", res);
        return res;
    }

    @Override
    @Cacheable(key = "\"product:\"+#id")
    public Product find(Long id) throws Exception {
        Product product = productRepository.findOne(id);
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnCallback(returnCallback);
        rabbitTemplate.convertAndSend(Constants.PRODUCT_EXCHANGE, "product.new:string", mapper.writeValueAsString(product));
        return product;
    }

    @Override
    @Cacheable
    public List<Product> list() throws Exception {
        List<Product> productList = productRepository.findAll();
        return productList;
    }
}
