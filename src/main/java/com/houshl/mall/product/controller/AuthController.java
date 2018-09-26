package com.houshl.mall.product.controller;

import com.houshl.mall.product.response.ObjectResponse;
import com.houshl.mall.product.response.ResponseUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    public ObjectResponse require() {
        return ResponseUtils.noLogin();
    }

}
