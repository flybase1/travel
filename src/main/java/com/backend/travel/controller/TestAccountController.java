package com.backend.travel.controller;

import com.backend.travel.POJO.DTO.ResetAccountPwd;
import com.backend.travel.POJO.entity.Account;
import com.backend.travel.common.BaseResponse;
import com.backend.travel.common.ErrorCode;
import com.backend.travel.common.ResultUtils;
import com.backend.travel.execption.BusinessException;
import com.backend.travel.service.impl.AccountServiceImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Objects;

@RestController
@RequestMapping( "/sysAccount" )
public class TestAccountController {

    @Resource
    private AccountServiceImpl accountService;
    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping( "/save" )
    @PreAuthorize( "hasAuthority('sys:user:edit') " + "||" + "hasAuthority('sys:user:update') " )
    public BaseResponse<Object> saveAccount(@RequestBody Account account) {
        if (account.getAccountId() == null) {
            return ResultUtils.error(ErrorCode.PARAMS_ERROR);
        } else {
            boolean update = accountService.updateById(account);
            if (update) {
                return ResultUtils.success("更新成功");
            }
        }
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR);
    }

    @PostMapping( "/resetPwd" )
    @PreAuthorize( "hasAuthority('sys:user:edit') " )
    public BaseResponse<Object> resetPwd(@RequestBody ResetAccountPwd resetAccountPwd) {
        Account account = accountService.getById(resetAccountPwd.getAccountId());
        if (account == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        String userPassword = account.getUserPassword();
        String oldPwd = resetAccountPwd.getOldPwd();
        if (bCryptPasswordEncoder.matches(oldPwd, userPassword)) {
            String newPwd = resetAccountPwd.getNewPwd();
            String encode = bCryptPasswordEncoder.encode(newPwd);
            account.setUserPassword(encode);
            boolean b = accountService.updateById(account);
            if (!b) {
                throw new BusinessException(ErrorCode.SYSTEM_ERROR);
            }
            return ResultUtils.success("更新成功");
        } else if (!bCryptPasswordEncoder.matches(oldPwd, userPassword)) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "旧密码输入错误");
        } else if (!Objects.equals(resetAccountPwd.getConfirmPwd(), resetAccountPwd.getNewPwd())) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "两次密码输入不一致");
        }
        return ResultUtils.success("ok");
    }
}
