package com.backend.travel.config;


import com.backend.travel.common.security.LoginFailureHandler;
import com.backend.travel.common.security.LoginSuccessHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity( jsr250Enabled = true )
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // 注意这里需要写入实际的地址,不需要写/api
    private static final String[] URL_WHITELIST = {
            "/api/login",
            "/test/test",
            "/test/list",
            "/doc.html",
            "/doc.html/**",
            "/doc.html#/**",
            "/v2/**",
            "/webjars/**", "/swagger-resources/**", "/v3/api-docs/**",
    };
    @Resource
    private LoginSuccessHandler loginSuccessHandler;
    @Resource
    private LoginFailureHandler loginFailureHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 跨域以及csrf攻击
        http
                .cors()
                .configurationSource(corsConfigurationSource())
                .and()
                .csrf()
                .disable()
                // 登入,登出
                .formLogin()
                .loginProcessingUrl("/login")
                .successHandler(loginSuccessHandler)
                .failureHandler(loginFailureHandler)
//                .and()
//                .logout()
                //.logoutSuccessHandler()
                // session配置，无状态
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                // 拦截规则配置,放行白名单
                .and()
                .authorizeRequests()
                .antMatchers(URL_WHITELIST).permitAll()
                .anyRequest().authenticated();

        // 异常处理

        // 自定义过滤

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
