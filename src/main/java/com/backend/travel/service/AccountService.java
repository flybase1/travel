package com.backend.travel.service;

import com.backend.travel.POJO.DTO.AccountPageDto;
import com.backend.travel.POJO.DTO.UserPageDto;
import com.backend.travel.POJO.VO.AccountPageVo;
import com.backend.travel.POJO.VO.UserPageVo;
import com.backend.travel.POJO.entity.Account;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

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
}
