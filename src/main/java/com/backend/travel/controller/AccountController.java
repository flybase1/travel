package com.backend.travel.controller;

import com.backend.travel.POJO.DTO.AccountDto.AccountInfoVo;
import com.backend.travel.POJO.DTO.AccountDto.AccountPageDto;
import com.backend.travel.POJO.DTO.AccountDto.AccountSaveDto;
import com.backend.travel.POJO.DTO.AccountDto.AccountUpdateDto;
import com.backend.travel.POJO.VO.AccountPageVo;
import com.backend.travel.POJO.entity.Account;
import com.backend.travel.common.BaseResponse;
import com.backend.travel.common.ResultUtils;
import com.backend.travel.service.impl.AccountServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.security.access.prepost.PreAuthorize;
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

    /**
     * 新建账号信息
     *
     * @param accountSaveDto
     * @return
     */
    @PostMapping( "/saveAccount" )
    @PreAuthorize( "hasAuthority('system:account:edit') " + " ||" + "hasAnyAuthority('system:account:add')" )
    public BaseResponse<Boolean> saveAccount(@RequestBody AccountSaveDto accountSaveDto) {
        Boolean saveSuccess = accountService.saveAccount(accountSaveDto);
        return ResultUtils.success(saveSuccess);
    }

    /**
     * 根据账号id修改账号信息
     *
     * @param accountUpdateDto
     * @return
     */
    @PutMapping( "/updateAccount" )
    @PreAuthorize( "hasAuthority('system:account:edit') " + " ||" + "hasAnyAuthority('system:account:update')" )
    public BaseResponse<Boolean> updateAccount(@RequestBody AccountUpdateDto accountUpdateDto) {
        Boolean updateSuccess = accountService.updateAccount(accountUpdateDto);
        return ResultUtils.success(updateSuccess);
    }

    /**
     * 根据账号id获取账号信息
     *
     * @param accountId
     * @return
     */
    @GetMapping( "/getAccountInfo" )
    @PreAuthorize( "hasAuthority('system:account:query') " )
    public BaseResponse<AccountInfoVo> getAccountInfo(@RequestParam Long accountId) {
        AccountInfoVo accountInfoVo = accountService.getAccountInfo(accountId);
        return ResultUtils.success(accountInfoVo);
    }


    /**
     * 根据id删除单个数据
     *
     * @param accountId
     * @return
     */
    @DeleteMapping( "/deleteAccount" )
    @PreAuthorize( "hasAuthority('system:account:delete') " )
    public BaseResponse<Boolean> deleteAccount(@RequestParam Long accountId) {
        Boolean deleteSuccess = accountService.deleteAccountById(accountId);
        return ResultUtils.success(deleteSuccess);
    }

    /**
     * 批量删除
     *
     * @param accountIds
     * @return
     */
    @DeleteMapping( "/delete/AccountList" )
    @PreAuthorize( "hasAuthority('system:account:delete') " )
    public BaseResponse<Boolean> deleteAccountList(@RequestBody Long[] accountIds) {
        Boolean deleteSuccess = accountService.deleteAccountByIds(accountIds);
        return ResultUtils.success(deleteSuccess);
    }

    /**
     * 重置密码
     *
     * @param accountId
     * @return
     */
    @GetMapping( "/resetPwd" )
    @PreAuthorize( "hasAuthority('system:account:role') " )
    public BaseResponse<Boolean> resetPwd(@RequestParam Long accountId) {
        Boolean resetSuccess = accountService.resetAccountPwd(accountId);
        return ResultUtils.success(resetSuccess);
    }

    /**
     * 更新账号状态
     *
     * @param accountId
     * @param accountStatus
     * @return
     */
    @GetMapping( "/updateStatus/{accountId}/status/{accountStatus}" )
    @PreAuthorize( "hasAuthority('system:account:role') " )
    public BaseResponse<Boolean> updateAccountStatus(
            @PathVariable( value = "accountId" ) Long accountId,
            @PathVariable( value = "accountStatus" ) Integer accountStatus) {
        Account account = accountService.getOne(new QueryWrapper<Account>().eq("accountId", accountId));
        account.setAccountStatus(accountStatus);
        boolean b = accountService.updateById(account);
        return ResultUtils.success(b);
    }

    /**
     * 根据账号id更新用户权限
     *
     * @param accountId
     * @param roleIds
     * @return
     */
    @PostMapping( "/grantRole/{accountId}" )
    @PreAuthorize( "hasAuthority('system:account:role') " )
    public BaseResponse<Boolean> grantRole(@PathVariable( "accountId" ) Long accountId, @RequestBody Integer[] roleIds) {
        Boolean success = accountService.grantRole(accountId, roleIds);
        return ResultUtils.success(success);
    }
}
