package com.backend.travel.controller;

import com.backend.travel.POJO.DTO.AccountPageDto;
import com.backend.travel.POJO.VO.AccountPageVo;
import com.backend.travel.common.BaseResponse;
import com.backend.travel.common.ResultUtils;
import com.backend.travel.service.impl.AccountServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping( "/account" )
public class AccountController {
    @Resource
    private AccountServiceImpl accountService;

    /**
     * 分页获取账号信息
     *
     * @param accountPageDto
     * @return
     */
    @PostMapping( "/page" )
    public BaseResponse<Page<AccountPageVo>> accountPageVo(@RequestBody AccountPageDto accountPageDto) {
        Page<AccountPageVo> accountInfoPage = accountService.getAccountInfoPage(accountPageDto);
        return ResultUtils.success(accountInfoPage);
    }
}
