package com.houshl.mall.product.activemq;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by houshuanglong on 2018/7/12.
 */
@Service
public class Producer {

    @Autowired
    JmsTemplate jmsTemplate;

    public void sendMessage(String dest, String message) {
        Destination destination = new ActiveMQQueue(dest);
        jmsTemplate.convertAndSend(destination, message);
    }


}
