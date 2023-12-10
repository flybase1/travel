package com.backend.travel.common.security.filter;

import cn.hutool.core.lang.UUID;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.backend.travel.common.ErrorCode;
import com.backend.travel.common.security.handler.LoginFailureHandler;
import com.backend.travel.constant.CheckCode;
import com.backend.travel.execption.BusinessException;
import com.backend.travel.execption.ValidateCodeException;
import com.backend.travel.key.Constants;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
@Slf4j
public class ImageCodeValidateFilter extends OncePerRequestFilter {

    private final String codeParamter = "code";  // 前端输入的图形验证码参数名
    @Resource
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private LoginFailureHandler authenticationFailureHandler;  // 自定义认证失败处理器

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 非 POST 方式的表单提交请求不校验图形验证码
        log.error("现在的请求是" + request.getRequestURI());
        log.error(request.getMethod());
        if (("/api/login".equals(request.getRequestURI())) && "POST".equals(request.getMethod())) {
            try {
                // 校验图形验证码合法性
                validate(request);
            } catch (ValidateCodeException e) {
                // 手动捕获图形验证码校验过程抛出的异常，将其传给失败处理器进行处理
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }

        // 放行请求，进入下一个过滤器
        filterChain.doFilter(request, response);
    }

    // 判断验证码的合法性
    private void validate(HttpServletRequest request) {
        // 获取用户传入的图形验证码值
        String requestCode = request.getParameter(this.codeParamter);
        if (requestCode == null) {
            requestCode = "";
        }
        requestCode = requestCode.trim();
        log.info("requestCode-----" + requestCode);
        UUID uuid = UUID.fromString(request.getParameter("uuid"));
        // 从redis里面获取相应的uuid的村的code数据
        String saveCode = redisTemplate.opsForValue().get(uuid.toString());
        log.info("saveCode-----" + saveCode);
        if (StringUtils.isBlank(saveCode)) {
            throw new ValidateCodeException("验证码的值不能为空");
        }
        if (!saveCode.equalsIgnoreCase(requestCode)) {
            throw new ValidateCodeException("验证码不正确");
        }
        // 用完删除key，不论有没有成功
        redisTemplate.delete(uuid.toString());
    }
}