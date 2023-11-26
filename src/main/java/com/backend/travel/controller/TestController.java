package com.backend.travel.controller;

import cn.hutool.core.util.StrUtil;
import com.backend.travel.POJO.entity.Account;
import com.backend.travel.common.BaseResponse;
import com.backend.travel.common.ErrorCode;
import com.backend.travel.common.ResultUtils;
import com.backend.travel.service.impl.AccountServiceImpl;
import com.backend.travel.utils.JWTUtils;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@RequestMapping( "/test" )
@RestController
public class TestController {
    @Resource
    private AccountServiceImpl accountService;

    @GetMapping( "/test" )
    public String hello() {
        return "hello";
    }

    @GetMapping( "/auth/list" )
    @PreAuthorize( "hasRole('ROLE_admin')" )
    //权限
    //@PreAuthorize("hasAuthority('system:user:list')")
    public BaseResponse<Object> listAccount(@RequestHeader( required = false ) String token) {
        HashMap<String, Object> map = new HashMap<>();
        if (StrUtil.isNotEmpty(token)) {
            List<Account> list = accountService.list();
            map.put("userList", list);
            return ResultUtils.success(map);
        } else {
            return ResultUtils.error(ErrorCode.NO_AUTH_ERROR);
        }
    }

    @GetMapping( "/noAuth/list" )
    public BaseResponse<Object> listAccountNoAuth() {
        List<Account> list = accountService.list();
        return ResultUtils.success(list);
    }

    @GetMapping( "/login" )
    public BaseResponse<HashMap<String, Object>> userLogin() {
        String token = JWTUtils.createJWT("122", "test", 1000 * 60);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("token", token);
        return ResultUtils.success(hashMap);
    }
}
