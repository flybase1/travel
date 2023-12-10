package com.backend.travel.common.mobileSecurity;

import com.backend.travel.common.ErrorCode;
import com.backend.travel.common.security.MyUserDetailService;
import com.backend.travel.execption.BusinessException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 短信登陆鉴权 Provider，要求实现 AuthenticationProvider 接口
 *
 * @author jitwxs
 * @since 2019/1/9 13:59
 */

public class MobileAuthenticationProvider implements AuthenticationProvider {

    private MyUserDetailService userDetailsService;

    private RedisTemplate<String, String> redisTemplate;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        MobileAuthenticationToken authenticationToken = (MobileAuthenticationToken) authentication;
        // 取出手机号
        String mobile = authentication.getPrincipal() == null ? "NONE_PROVIDED" : authentication.getName();
        // 自定义手机号从数据库中查询用户信息
        UserDetails userDetails = userDetailsService.loadUserByUsername(mobile);
        //未查询到用户信息，抛出异常
        if (userDetails == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "手机号未注册");
        }
        // 验证码校验
        checkSmsCode(mobile);

        // 此时鉴权成功后，应当重新 new 一个拥有鉴权的 authenticationResult 返回
        MobileAuthenticationToken authenticationResult = new MobileAuthenticationToken(userDetails, userDetails.getAuthorities());

        authenticationResult.setDetails(authenticationToken.getDetails());
        return authenticationResult;
    }

    private void checkSmsCode(String mobile) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String inputCode = request.getParameter("smsCode");

        String smsCode = redisTemplate.opsForValue().get(mobile);

        if (smsCode == null) {
            throw new BadCredentialsException("未检测到申请验证码");
        }

        if (!smsCode.equalsIgnoreCase(inputCode)) {
            throw new BadCredentialsException("验证码错误");
        }
    }


    /**
     * ProviderManager 管理器通过此方法来判断是否采用此 AuthenticationProvider 类
     * 来处理由 AuthenticationFilter 过滤器传入的 Authentication 对象
     */
    @Override
    public boolean supports(Class<?> authentication) {
        // 判断 authentication 是不是 MobileAuthenticationToken 的子类或子接口
        // isAssignableFrom 返回 true 当且仅当调用者为父类.class，参数为本身或者其子类.class
        // ProviderManager 会获取 MobileAuthenticationFilter 过滤器传入的 Authentication 类型
        // 所以当且仅当 authentication 的类型为 MobileAuthenticationToken 才返回 true
        return MobileAuthenticationToken.class.isAssignableFrom(authentication);
    }


    public MyUserDetailService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(MyUserDetailService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public RedisTemplate<String, String> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
