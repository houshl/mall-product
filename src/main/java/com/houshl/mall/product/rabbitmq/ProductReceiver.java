package com.houshl.mall.product.rabbitmq;

import com.houshl.mall.product.common.Constants;
import com.houshl.mall.product.model.Product;
import com.rabbitmq.client.AMQP;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * Created by houshuanglong on 2018/8/14.
 */
@Component
@RabbitListener(bindings = @QueueBinding(
        value = @Queue(value = Constants.PRODUCT_NEW_QUEUE, durable = "true"),
        exchange = @Exchange(value = Constants.PRODUCT_EXCHANGE, durable = "true", ignoreDeclarationExceptions = "true"),
        key = "product:new"
))
@Slf4j
public class ProductReceiver {

    @RabbitHandler
    public void onMessage(@Payload Product product) {
        log.info("{}--{}--{}", product.getId(), product.getName(), product.getPrice());
    }

}
