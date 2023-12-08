package com.backend.travel.execption;

import org.springframework.security.core.AuthenticationException;

/**
 * 校验验证码是否正确
 */
public class ValidateCodeException extends AuthenticationException {
    public ValidateCodeException(String msg, Throwable t) {
        super(msg, t);
    }

    public ValidateCodeException(String msg) {
        super(msg);
    }
}