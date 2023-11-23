package com.backend.travel.common.security;

import cn.hutool.json.JSONUtil;
import com.backend.travel.common.ResultUtils;
import com.backend.travel.utils.JWTUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
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
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        ServletOutputStream outputStream = response.getOutputStream();
        String userName = "user";
        String token = JWTUtils.getJwtToken(1L, userName, "1");
        HashMap<String, Object> map = new HashMap<>();
        map.put("authorization", token);
        outputStream.write(JSONUtil.toJsonStr(ResultUtils.success(map)).getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}
