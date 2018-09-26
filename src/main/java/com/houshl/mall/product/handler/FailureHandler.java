package com.houshl.mall.product.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.houshl.mall.product.response.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by houshuanglong on 2018/9/16.
 */
@Slf4j
@Component
public class FailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    ObjectMapper mapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json;charset=UTF-8");
        exception.printStackTrace();
        response.getWriter().write(mapper.writeValueAsString(ResponseUtils.noLogin()));

    }

}
