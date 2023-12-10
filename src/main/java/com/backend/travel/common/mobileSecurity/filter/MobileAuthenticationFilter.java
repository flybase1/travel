package com.backend.travel.common.mobileSecurity.filter;

import com.backend.travel.common.mobileSecurity.MobileAuthenticationToken;
import io.jsonwebtoken.lang.Assert;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 短信登录的鉴权过滤器，模仿 UsernamePasswordAuthenticationFilter 实现
 */
public class MobileAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    /**
     * form表单中手机号码的字段name
     */
    public static final String SPRING_SECURITY_FORM_MOBILE_KEY = "userPhoneNum";

    private String mobileParameter = SPRING_SECURITY_FORM_MOBILE_KEY;
    /**
     * 是否仅 POST 方式
     */
    private boolean postOnly = true;

    public MobileAuthenticationFilter() {
        // 短信登录的请求 post 方式的 /phoneLogin
        super(new AntPathRequestMatcher("/phoneLogin", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        //(1) 默认情况下，如果请求方式不是 POST，会抛出异常
        if (postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else {
            //(2) 获取请求携带的 mobile
            String mobile = obtainMobile(request);

            if (mobile == null) {
                mobile = "";
            }

            mobile = mobile.trim();
            //(3) 使用前端传入的 mobile 构造 Authentication 对象，标记该对象未认证
            MobileAuthenticationToken authRequest = new MobileAuthenticationToken(mobile);

            //(4) 将请求中的一些属性信息设置到 Authentication 对象中，如：remoteAddress，sessionId
            this.setDetails(request, authRequest);

            return this.getAuthenticationManager().authenticate(authRequest);
        }
    }

    @Nullable
    protected String obtainMobile(HttpServletRequest request) {
        return request.getParameter(mobileParameter);
    }

    protected void setDetails(HttpServletRequest request, MobileAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }

    public String getMobileParameter() {
        return mobileParameter;
    }

    public void setMobileParameter(String mobileParameter) {
        Assert.hasText(mobileParameter, "Mobile parameter must not be empty or null");
        this.mobileParameter = mobileParameter;
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }
}

