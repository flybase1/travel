package com.backend.travel.execption;

import com.backend.travel.common.BaseResponse;

import com.backend.travel.common.ErrorCode;
import com.backend.travel.common.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler( BusinessException.class )
    public BaseResponse<?> businessExceptionHandler(BusinessException e) {
        log.error("businessException: " + e.getMessage(), e);
        return ResultUtils.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler( RuntimeException.class )
    public BaseResponse<?> runtimeExceptionHandler(RuntimeException e) {
        log.error("runtimeException", e);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR, e.getMessage());
    }


    /**
     * @RequestBody 上校验失败后抛出的异常是 MethodArgumentNotValidException 异常。
     */
    @ExceptionHandler( value = MethodArgumentNotValidException.class )
    public BaseResponse<?> handler(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        ObjectError objectError = bindingResult.getAllErrors().stream().findFirst().get();
        log.error("实体类校验异常：-------------{}", objectError.getDefaultMessage());
        return ResultUtils.error(ErrorCode.PARAMS_ERROR, objectError.getDefaultMessage());
    }

    /**
     * 不加 @RequestBody注解，校验失败抛出的则是 BindException
     */
    @ExceptionHandler( value = BindException.class )
    public BaseResponse<?> exceptionHandler(BindException e) {
        String messages = e.getBindingResult().getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining("；"));
        return ResultUtils.error(ErrorCode.PARAMS_ERROR, messages);
    }

    /**
     * @RequestParam 上校验失败后抛出的异常是 ConstraintViolationException
     */
    @ExceptionHandler( {ConstraintViolationException.class} )
    public BaseResponse<?> methodArgumentNotValid(ConstraintViolationException exception) {
        String message = exception.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining("；"));
        return ResultUtils.error(ErrorCode.PARAMS_ERROR, message);
    }

}
