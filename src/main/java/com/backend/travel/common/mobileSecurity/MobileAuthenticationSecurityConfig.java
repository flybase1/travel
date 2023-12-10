package com.backend.travel.common.mobileSecurity;

import com.backend.travel.common.mobileSecurity.filter.MobileCodeValidateFilter;
import com.backend.travel.common.mobileSecurity.filter.MobileAuthenticationFilter;
import com.backend.travel.common.security.MyUserDetailService;
import com.backend.travel.common.security.handler.LoginFailureHandler;
import com.backend.travel.common.security.handler.LoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class MobileAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    @Autowired
    private MyUserDetailService phoneDetailService;
    @Autowired
    private MobileCodeValidateFilter mobileCodeValidaterFilter;  // 手机短信验证码校验过滤器
    @Autowired
    private LoginSuccessHandler customAuthenticationSuccessHandler;
    @Autowired
    private LoginFailureHandler customAuthenticationFailureHandler;
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void configure(HttpSecurity http) {
        //(1) 将短信验证码认证的自定义过滤器绑定到 HttpSecurity 中
        //(1.1) 创建手机短信验证码认证过滤器的实例 filer
        MobileAuthenticationFilter filter = new MobileAuthenticationFilter();
        //(1.2) 设置 filter 使用 AuthenticationManager(ProviderManager 接口实现类) 认证管理器
        // 多种登录方式应该使用同一个认证管理器实例，所以获取 Spring 容器中已经存在的 AuthenticationManager 实例
        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
        filter.setAuthenticationManager(authenticationManager);
        //(1.3) 设置 filter 使用自定义成功和失败处理器
        filter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);
        filter.setAuthenticationFailureHandler(customAuthenticationFailureHandler);
        //(1.4) 设置 filter 使用 SessionAuthenticationStrategy 会话管理器
        // 多种登录方式应该使用同一个会话管理器实例，获取 Spring 容器已经存在的 SessionAuthenticationStrategy 实例
//        SessionAuthenticationStrategy sessionAuthenticationStrategy = http.getSharedObject(SessionAuthenticationStrategy.class);
//        filter.setSessionAuthenticationStrategy(sessionAuthenticationStrategy);
        //(1.5) 在 UsernamePasswordAuthenticationFilter 过滤器之前添加 MobileCodeValidateFilter 过滤器
        // 在 UsernamePasswordAuthenticationFilter 过滤器之后添加 MobileAuthenticationFilter 过滤器
        http.addFilterBefore(mobileCodeValidaterFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterAfter(filter, UsernamePasswordAuthenticationFilter.class);

        //(2) 将自定义的 MobileAuthenticationProvider 处理器绑定到 HttpSecurity 中
        //(2.1) 创建手机短信验证码认证过滤器的 AuthenticationProvider 实例，并指定所使用的 UserDetailsService
        MobileAuthenticationProvider provider = new MobileAuthenticationProvider();
        provider.setUserDetailsService(phoneDetailService);
        provider.setRedisTemplate(redisTemplate);

        //(2.2) 将该 AuthenticationProvider 实例绑定到 HttpSecurity 中
        http.authenticationProvider(provider);
    }
}
