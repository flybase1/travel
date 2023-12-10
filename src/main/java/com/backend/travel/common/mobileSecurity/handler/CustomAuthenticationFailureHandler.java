package com.backend.travel.common.mobileSecurity.handler;

import com.backend.travel.common.ErrorCode;
import com.backend.travel.common.ResultUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 继承 SimpleUrlAuthenticationFailureHandler 处理器，该类是 failureUrl() 方法使用的认证失败处理器
 */
@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Resource
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        String xRequestedWith = request.getHeader("x-requested-with");
        // 判断前端的请求是否为 ajax 请求
        if ("XMLHttpRequest".equals(xRequestedWith)) {
            // 认证成功，响应 JSON 数据
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(objectMapper.writeValueAsString(ResultUtils.error(ErrorCode.NO_AUTH_ERROR, "登录错误")));
        } else {
            // 用户名、密码方式登录出现认证异常，需要重定向到 /login/page?error
            // 手机短信验证码方式登录出现认证异常，需要重定向到 /mobile/page?error
            // 使用 Referer 获取当前登录表单提交请求是从哪个登录页面(/login/page 或 /mobile/page)链接过来的
            String refer = request.getHeader("Referer");
            String lastUrl = StringUtils.substringBefore(refer, "?");
            // 设置默认的重定向路径
            super.setDefaultFailureUrl(lastUrl + "?error");
            // 调用父类的 onAuthenticationFailure() 方法
            super.onAuthenticationFailure(request, response, e);
        }
    }
}