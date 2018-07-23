package com.houshl.mall.product.exception;

import com.houshl.mall.product.response.ObjectResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by houshuanglong on 2018/7/23.
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(PromptException.class)
    public ObjectResponse promptExceptionHandler(PromptException ex) {
        log.info(ex.getMessage());
        ObjectResponse response = new ObjectResponse(ex.getCode(), ex.getMessage());
        return response;
    }

    @ExceptionHandler(Exception.class)
    public ObjectResponse exceptionHandler(Exception ex) {
        log.error(ex.getMessage(), ex);
        ObjectResponse response = new ObjectResponse(ExceptionCode.EX_CODE, "服务繁忙, 请稍后重试");
        return response;
    }

}
