package com.houshl.mall.product.response;

import com.houshl.mall.product.common.Constants;
import org.springframework.http.HttpStatus;

/**
 * Created by houshuanglong on 2018/7/23.
 */
public class ResponseUtils {

    public static ObjectResponse ok() {
        ObjectResponse response = new ObjectResponse(Constants.OK, "OK");
        return response;
    }

    public static ObjectResponse ok(Object data) {
        ObjectResponse response = new ObjectResponse(Constants.OK, "OK", data);
        return response;
    }

    public static ObjectResponse noLogin() {
        ObjectResponse response = new ObjectResponse(HttpStatus.FORBIDDEN.value(), "Forbidden");
        return response;
    }

}
