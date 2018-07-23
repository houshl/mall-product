package com.houshl.mall.product.response;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by houshuanglong on 2018/7/23.
 */
public class ObjectResponse {

    @Getter
    @Setter
    private int code;

    @Getter
    @Setter
    private String message;

    @Getter
    @Setter
    private Object data;

    public ObjectResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ObjectResponse(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

}
