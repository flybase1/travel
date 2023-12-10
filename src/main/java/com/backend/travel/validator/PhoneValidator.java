package com.backend.travel.validator;

import com.backend.travel.ValidtorAnnotation.Phone;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 手机验证注解
 */
public class PhoneValidator implements ConstraintValidator<Phone, String> {

    private static final String REGEX = "^1[3456789]\\\\d{9}$";

    /**
     * @param value
     * @param context
     * @return：返回 true 表示效验通过
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // 不为null才进行校验
        if (value != null) {
            return value.matches(REGEX);
        }
        return true;
    }
}
