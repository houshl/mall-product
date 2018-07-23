package com.houshl.mall.product.exception;

import com.houshl.mall.product.common.Constants;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by houshuanglong on 2018/7/23.
 */
public class PromptException extends RuntimeException {

    @Getter
    @Setter
    private int code = Constants.PROMPT_EX_CODE;

    @Getter
    @Setter
    private String message;

    public PromptException(String message) {
        this.message = message;
    }

    public PromptException(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
