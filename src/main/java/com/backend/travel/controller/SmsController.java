package com.backend.travel.controller;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;
import com.backend.travel.POJO.entity.Account;
import com.backend.travel.common.BaseResponse;
import com.backend.travel.common.ErrorCode;
import com.backend.travel.common.ResultUtils;
import com.backend.travel.execption.BusinessException;
import com.backend.travel.service.impl.AccountServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * 获取短信验证码
 */
@RequestMapping( "/sms" )
@RestController
@Slf4j
public class SmsController {
    @Resource
    private RedisTemplate<String, String> redisTemplate;
    @Resource
    private AccountServiceImpl accountService;

    @GetMapping( "/smsCode" )
    public BaseResponse<String> getSmsCodeByPhone(@RequestParam String phone) {
        if (phone.length() != 11) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "手机号长度不符合规定");
        }
        Account account = accountService.getOne(new QueryWrapper<Account>().eq("userPhoneNum", phone));
        if (account == null) {
            throw new BusinessException(ErrorCode.PHONE_NOT_EXIST, "手机号未被注册");
        }
        String smsCode = RandomUtil.randomNumbers(6);
        redisTemplate.opsForValue().set(phone, smsCode, 60 * 5, TimeUnit.MINUTES);
        log.info("手机号为：{}，短信验证码为：{}", phone, smsCode);
        return ResultUtils.success("发送成功，请注意接收");
    }
}
