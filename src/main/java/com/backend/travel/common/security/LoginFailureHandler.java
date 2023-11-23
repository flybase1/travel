package com.backend.travel.common.security;

import cn.hutool.json.JSONUtil;
import com.backend.travel.utils.JWTUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

/**
 * 登陆成功处理器
 */
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        ServletOutputStream outputStream = response.getOutputStream();
        String message = exception.getMessage();
        if (exception instanceof BadCredentialsException) {
            message = "用户名字或者密码错误";
        }
        outputStream.write(JSONUtil.toJsonStr(message).getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}
