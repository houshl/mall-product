package com.houshl.mall.product.controller;

import com.houshl.mall.product.activemq.Producer;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Destination;

/**
 * Created by houshuanglong on 2018/7/13.
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    Producer producer;

    @RequestMapping("/t1")
    public String t1() {
        producer.sendMessage("aaa", "nnn");
        return "OK";
    }
}
