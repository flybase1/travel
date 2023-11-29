package com.backend.travel.service;

import com.backend.travel.POJO.DTO.AccountDto.AccountInfoVo;
import com.backend.travel.POJO.DTO.AccountDto.AccountPageDto;
import com.backend.travel.POJO.DTO.AccountDto.AccountSaveDto;
import com.backend.travel.POJO.DTO.AccountDto.AccountUpdateDto;
import com.backend.travel.POJO.VO.AccountPageVo;
import com.backend.travel.POJO.entity.Account;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author admin
 * @description 针对表【account(账号表)】的数据库操作Service
 * @createDate 2023-11-23 10:52:26
 */
public interface AccountService extends IService<Account> {

    /**
     * 根据username获取数据
     *
     * @param username
     * @return
     */
    Account getByUserName(String username);

    /**
     * 获取权限菜单
     *
     * @param accountId
     * @return
     */
    String getUserAuthorityInfo(Long accountId);

    /**
     * 账号分页查询
     *
     * @param accountPageDto
     * @return
     */
    Page<AccountPageVo> getAccountInfoPage(AccountPageDto accountPageDto);

    /**
     * 新建账号
     * @param accountSaveDto
     * @return
     */
    Boolean saveAccount(AccountSaveDto accountSaveDto);

    /**
     * 账号信息修改
     * @param accountUpdateDto
     * @return
     */
    Boolean updateAccount(AccountUpdateDto accountUpdateDto);

    /**
     * 根据账号id获取账号信息
     * @param accountId
     * @return
     */
    AccountInfoVo getAccountInfo(Long accountId);

    /**
     * 根据id删除账号信息
     * @param accountId
     * @return
     */
    Boolean deleteAccountById(Long accountId);

    /**
     * 批量删除
     * @param accountIds
     * @return
     */
    Boolean deleteAccountByIds(Long[] accountIds);

    /**
     * 重置密码
     * @param accountId
     * @return
     */
    Boolean resetAccountPwd(Long accountId);

    /**
     * 授予权限
     * @param accountId
     * @param roleIds
     * @return
     */
    Boolean grantRole(Long accountId, Integer[] roleIds);
}
