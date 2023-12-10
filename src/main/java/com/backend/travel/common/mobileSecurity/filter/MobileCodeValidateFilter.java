package com.backend.travel.common.mobileSecurity.filter;

import com.backend.travel.common.mobileSecurity.handler.CustomAuthenticationFailureHandler;
import com.backend.travel.common.security.filter.JwtAuthenticationFilter;
import com.backend.travel.common.security.handler.LoginFailureHandler;
import com.backend.travel.execption.ValidateCodeException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 手机短信验证码校验
 */
@Component
@Slf4j
public class MobileCodeValidateFilter extends OncePerRequestFilter {
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    private final String codeParamter = "smsCode";  // 前端输入的手机短信验证码参数名

    @Autowired
    private LoginFailureHandler authenticationFailureHandler; // 自定义认证失败处理器

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("开始校验手机短信验证码" + request.getRequestURI());
        // 非 POST 方式的手机短信验证码提交请求不进行校验
        if ("/api/phoneLogin".equals(request.getRequestURI()) && "POST".equals(request.getMethod())) {
            try {
                // 检验手机验证码的合法性
                validate(request);

            } catch (ValidateCodeException e) {
                // 将异常交给自定义失败处理器进行处理
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }

        // 放行，进入下一个过滤器
        filterChain.doFilter(request, response);
    }

    /**
     * 检验用户输入的手机验证码的合法性
     */
    private void validate(HttpServletRequest request) {
        // 获取用户传入的手机验证码值
        String requestCode = request.getParameter(this.codeParamter);
        if (requestCode == null) {
            requestCode = "";
        }
        requestCode = requestCode.trim();

        String userPhoneNum = request.getParameter("userPhoneNum");
        String smsCode = redisTemplate.opsForValue().get(userPhoneNum);

        // 校验出错，抛出异常
        if (StringUtils.isBlank(requestCode)) {
            throw new ValidateCodeException("验证码的值不能为空");
        }

        if (smsCode == null) {
            throw new ValidateCodeException("验证码不存在");
        }

        if (!requestCode.equalsIgnoreCase(smsCode)) {
            throw new ValidateCodeException("验证码输入错误");
        }

        //  redisTemplate.delete(userPhoneNum);
    }
}