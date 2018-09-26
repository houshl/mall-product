package com.houshl.mall.product.rabbitmq;

import com.houshl.mall.product.common.Constants;
import com.houshl.mall.product.model.Product;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * Created by houshuanglong on 2018/8/14.
 */
@Component
@Slf4j
public class ProductReceiver {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = Constants.PRODUCT_NEW_QUEUE, durable = "true"),
            exchange = @Exchange(value = Constants.PRODUCT_EXCHANGE, durable = "true", ignoreDeclarationExceptions = "true"),
            key = "product:new"
    ))
    @RabbitHandler
    public void createProduct(@Payload Product product,
                          Channel channel,
                          @Headers Map<String, Object> headers) throws IOException {
        log.info("{}--{}--{}", product.getId(), product.getName(), product.getPrice());
        long deliveryTag = (long) headers.get(AmqpHeaders.DELIVERY_TAG);
        channel.basicAck(deliveryTag, false);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "string-queue", durable = "true"),
            exchange = @Exchange(value = Constants.PRODUCT_EXCHANGE, durable = "true", ignoreDeclarationExceptions = "true"),
            key = "product.new:string"
    ))
    @RabbitHandler
    public void find(Message message, Channel channel) throws IOException {
        log.info("{}", message.getPayload());
        long deliveryTag = (long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        channel.basicAck(deliveryTag, false);
    }

}
