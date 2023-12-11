package com.backend.travel.config;


import com.backend.travel.common.BaseResponse;
import com.backend.travel.common.ErrorCode;
import com.backend.travel.common.ResultUtils;
import com.backend.travel.common.mobileSecurity.MobileAuthenticationSecurityConfig;
import com.backend.travel.common.security.*;
import com.backend.travel.common.security.filter.ImageCodeValidateFilter;
import com.backend.travel.common.security.filter.JwtAuthenticationFilter;
import com.backend.travel.common.security.handler.JwtLogoutSuccessHandler;
import com.backend.travel.common.security.handler.LoginFailureHandler;
import com.backend.travel.common.security.handler.LoginSuccessHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity( jsr250Enabled = true )
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // 注意这里需要写入实际的地址,不需要写/api
    private static final String[] URL_WHITELIST = {
            "/api/login",
            "/test/test",
            "/test/noAuth/list",
            "/captcha/code",
            "/sms/smsCode",
            "/account/userRegister",
            "/api/phoneLogin",
            "/doc.html",
            "/doc.html/**",
            "/doc.html#/**",
            "/v2/**",
            "/webjars/**", "/swagger-resources/**", "/v3/api-docs/**",
    };
    // 登陆成功
    @Resource
    private LoginSuccessHandler loginSuccessHandler;
    // 登陆失败
    @Resource
    private LoginFailureHandler loginFailureHandler;
    // 详细信息
    @Resource
    private MyUserDetailService myUserDetailService;
    @Resource
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    // 登出配置
    @Resource
    private JwtLogoutSuccessHandler jwtLogoutSuccessHandler;

    @Resource
    private ImageCodeValidateFilter imageCodeValidateFilter; // 自定义过滤器（图形验证码校验）

    // jwt过滤器
    @Bean
    JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        return new JwtAuthenticationFilter(authenticationManager());
    }

    // 加密方式
    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailService);
    }

    @Resource
    private MobileAuthenticationSecurityConfig mobileAuthenticationSecurityConfig;


    /**
     * 定制基于 HTTP 请求的用户访问控制
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 跨域以及csrf攻击
        http
                .apply(mobileAuthenticationSecurityConfig)
                .and()
                .cors()
                .configurationSource(corsConfigurationSource())
                .and()
                .csrf()
                .disable()
                // 登入,登出
                .formLogin()
                // 设置登录页面的访问路径，默认为 /login，GET 请求；该路径不设限访问
                .loginProcessingUrl("/login")
                .successHandler(loginSuccessHandler)
                .failureHandler(loginFailureHandler)
                .and()
                .logout()
                .logoutSuccessHandler(jwtLogoutSuccessHandler)
                // session配置，无状态
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                // 手机验证码
                .and()
                // 拦截规则配置,放行白名单

                .authorizeRequests()
                // 以下访问不需要任何权限，任何人都可以访问
                .antMatchers(URL_WHITELIST).permitAll()
                // 其它任何请求访问都需要先通过认证
                .anyRequest().authenticated()

                // 异常处理
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                // 自定义过滤
                .and()
                .addFilterBefore(imageCodeValidateFilter, UsernamePasswordAuthenticationFilter.class)

                .addFilter(jwtAuthenticationFilter())
        // 禁止再次登录,挤下线功能
//                .sessionManagement()
//                .maximumSessions(1)
//                .expiredSessionStrategy(event -> {
//                    HttpServletResponse response = event.getResponse();
//                    response.setContentType("application/json;charset=utf-8");
//                    BaseResponse baseResponse = ResultUtils.error(ErrorCode.NOT_LOGIN_ERROR, "当前会话已经失效，重新登陆");
//                    String res = new ObjectMapper().writeValueAsString(baseResponse);
//                    response.setContentType("application/json;charset=utf-8");
//                    response.getWriter().println(res);
//                    response.flushBuffer();
//                })
//                // 登录之后禁止再次登录
//                .maxSessionsPreventsLogin(true)

        ;

    }


    /**
     * 跨域
     *
     * @return
     */
    private CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return urlBasedCorsConfigurationSource;
    }
}
