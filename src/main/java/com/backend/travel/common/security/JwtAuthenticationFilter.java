package com.backend.travel.common.security;

import cn.hutool.core.util.StrUtil;
import com.backend.travel.POJO.entity.Account;
import com.backend.travel.common.ErrorCode;
import com.backend.travel.constant.CheckResult;
import com.backend.travel.constant.JWTConstant;
import com.backend.travel.execption.BusinessException;
import com.backend.travel.service.impl.AccountServiceImpl;
import com.backend.travel.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * jwt拦截器
 */
@Slf4j
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    @Resource
    private AccountServiceImpl accountService;

    private static final String[] URL_WHITELIST = {
            "/api/login",
            "/api/testLogin",
            "/api/test/test",
            "/api/test/noAuth/list",
            "/doc.html",
            "/doc.html/**",
            "/doc.html#/**",
            "/v2/**",
            "/webjars/**", "/swagger-resources/**", "/v3/api-docs/**",
    };

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Resource
    private MyUserDetailService myUserDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader("token");
        log.info("放行URI" + request.getRequestURI());
        String requestURI = request.getRequestURI();
        // 如果token是空，url是白名单，放行
        if (StrUtil.isEmpty(token) || new ArrayList<>(Arrays.asList(URL_WHITELIST)).contains(requestURI)) {
            chain.doFilter(request, response);
            return;
        }
        CheckResult checkResult = JWTUtils.validateJWT(token);
        if (!checkResult.isSuccess()) {
            switch (checkResult.getErrCode()) {
                case JWTConstant.JWT_ERRCODE_NULL:
                    throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR, "Token 不存在");
                case JWTConstant.JWT_ERRCODE_EXPIRE:
                    throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR, "Token 已过期");
                case JWTConstant.JWT_ERRCODE_FAIL:
                    throw new BusinessException(ErrorCode.SYSTEM_ERROR, "Token 认证失败");
            }
        }
        // 解析jwt去获取用户名
        //Claims claims = checkResult.getClaims();
        Claims claims = JWTUtils.parseJWT(token);
        String userAccount = claims.getSubject();
        log.info("userAccount:" + userAccount);

        Account account = accountService.getByUserName(userAccount);
        List<GrantedAuthority> userAuthority = myUserDetailService.getUserAuthority(account.getAccountId());
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userAccount, null, userAuthority);
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        chain.doFilter(request, response);
    }
}
