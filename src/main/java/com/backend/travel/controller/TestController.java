package com.backend.travel.controller;

import cn.hutool.core.util.StrUtil;
import com.backend.travel.POJO.entity.Account;
import com.backend.travel.POJO.entity.User;
import com.backend.travel.common.BaseResponse;
import com.backend.travel.common.ErrorCode;
import com.backend.travel.common.ResultUtils;
import com.backend.travel.service.impl.AccountServiceImpl;
import com.backend.travel.service.impl.UserServiceImpl;
import com.backend.travel.utils.JWTUtils;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@RequestMapping( "/test" )
@RestController
public class TestController {
    @Resource
    private UserServiceImpl userService;
    @Resource
    private AccountServiceImpl accountService;

    @GetMapping( "/test" )
    public String hello() {
        return "hello";
    }

    @GetMapping( "/list" )
    public BaseResponse<Object> listAccount(@RequestHeader( required = false ) String token) {
        HashMap<String, Object> map = new HashMap<>();
        if (StrUtil.isNotEmpty(token)) {
            List<Account> list = accountService.list();
            map.put("userList", list);
            return ResultUtils.success(map);
        }else {
            return ResultUtils.error(ErrorCode.NO_AUTH_ERROR);
        }

    }

    @GetMapping( "/login" )
    public BaseResponse<HashMap<String, Object>> userLogin() {
        String token = JWTUtils.getJwtToken(1L, "test", "1");
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("token", token);
        return ResultUtils.success(hashMap);
    }
}
