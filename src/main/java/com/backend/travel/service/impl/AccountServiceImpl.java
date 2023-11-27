package com.backend.travel.service.impl;

import java.util.Date;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.backend.travel.POJO.DTO.AccountPageDto;
import com.backend.travel.POJO.DTO.UserPageDto;
import com.backend.travel.POJO.VO.AccountPageVo;
import com.backend.travel.POJO.VO.UserPageVo;
import com.backend.travel.POJO.entity.*;
import com.backend.travel.common.CommonConstant;
import com.backend.travel.dao.AccountMapper;
import com.backend.travel.utils.SqlUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.backend.travel.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author admin
 * @description 针对表【account(账号表)】的数据库操作Service实现
 * @createDate 2023-11-23 10:52:26
 */
@Service
@Slf4j
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account>
        implements AccountService {
    @Resource
    private SysAccountRoleServiceImpl sysAccountRoleService;
    @Resource
    private SysRoleServiceImpl sysRoleService;
    @Resource
    private SysRoleMenuServiceImpl sysRoleMenuService;
    @Resource
    private SysMenuServiceImpl sysMenuService;
    @Resource
    private UserServiceImpl userService;

    @Override
    public Account getByUserName(String userAccount) {
        QueryWrapper<Account> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        Account one = this.getOne(queryWrapper);
        return one;
    }

    @Override
    public String getUserAuthorityInfo(Long accountId) {
        StringBuffer authority = new StringBuffer();
        // 1. 账号信息获取角色信息
        // 1.1 匹配当前账号拥有的权限
        QueryWrapper<SysAccountRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("accountId", accountId);
        List<SysAccountRole> sysAccountRoleList = sysAccountRoleService.list(queryWrapper);
        // 1.2 匹配当前账号所有的权限信息
        List<SysRole> sysRoleList = sysAccountRoleList.stream().map(sysAccountRole -> {
            Integer roleId = sysAccountRole.getRoleId();
            return sysRoleService.getOne(new QueryWrapper<SysRole>().eq("roleId", roleId));
        }).collect(Collectors.toList());
        // 1.3 当前账号权限信息code进行拼接，以,分割
        if (sysAccountRoleList.size() > 0) {
            String roleCodeStr = sysRoleList.stream().map(sysRole -> "ROLE_" + sysRole.getRoleCode())
                    .collect(Collectors.joining(","));
            authority.append(roleCodeStr);
        }
        // 2. 遍历所有角色，获取所有的菜单权限,不重复
        Set<String> menuCodeSet = new HashSet<>();
        for (SysRole sysRole : sysRoleList) {
            // 遍历角色菜单表，找到所有对应的菜单id
            List<SysRoleMenu> sysRoleMenus = sysRoleMenuService.list(new QueryWrapper<SysRoleMenu>().eq("roleId", sysRole.getRoleId()));
            // 遍历所有菜单id，找到所有对应的菜单信息
            sysRoleMenus.forEach(sysRoleMenu -> {
                SysMenu sysMenu = sysMenuService.getOne(new QueryWrapper<SysMenu>().eq("id", sysRoleMenu.getMenuId()));
                log.info("perms:" + sysMenu.getPerms());
                // 去重
                if (sysMenu.getPerms() != null) {
                    menuCodeSet.add(sysMenu.getPerms());
                }
            });
        }
        if (menuCodeSet.size() > 0) {
            // 由于之前的角色信息的code是按照,分割，现在缺少一个,，需要加上
//            authority.append(",");
            String menuCodeStr = menuCodeSet.stream().collect(Collectors.joining(","));
            authority.append(menuCodeStr);
        }
        //
        log.info("authority:" + authority);
        return authority.toString();
    }

    @Override
    public Page<AccountPageVo> getAccountInfoPage(AccountPageDto accountPageDto) {
        String queryAccount = accountPageDto.getQueryAccount();
        String queryPhoneNum = accountPageDto.getQueryPhoneNum();
        String queryEmail = accountPageDto.getQueryEmail();
        Integer queryRoleId = accountPageDto.getQueryRoleId();
        Integer queryStatus = accountPageDto.getQueryStatus();
        long current = accountPageDto.getCurrent();
        long pageSize = accountPageDto.getPageSize();
        String sortField = accountPageDto.getSortField();
        String sortOrder = accountPageDto.getSortOrder();

        // 拼接查询条件
        QueryWrapper<Account> queryWrapper = new QueryWrapper<>();
        if (queryAccount != null) {
            queryWrapper.like("userAccount", queryAccount);
        }
        if (queryPhoneNum != null) {
            queryWrapper.like("userPhoneNum", queryPhoneNum);
        }
        if (queryEmail != null) {
            queryWrapper.like("userEmail", queryEmail);
        }
        if (queryRoleId != null) {
            queryWrapper.eq("permissionId", queryRoleId);
        }
        if (queryStatus != null) {
            queryWrapper.eq("accountStatus", queryStatus);
        }

        // 排序
        queryWrapper
                .orderBy(SqlUtils.validSortField(sortField),
                        sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                        sortField);
        Page<Account> accountPage = this.page(new Page<>(current, pageSize), queryWrapper);
        // 删除部分返回数据
        List<Account> accountPageRecords = accountPage.getRecords();
        List<AccountPageVo> accountPageVoList = accountPageRecords.stream().map(account -> {
            AccountPageVo accountPageVo = new AccountPageVo();
            BeanUtil.copyProperties(account, accountPageVo);
            return accountPageVo;
        }).collect(Collectors.toList());

        accountPageVoList.forEach(accountPageVo -> {
            Long accountId = accountPageVo.getAccountId();
            List<SysAccountRole> accountRoleList = sysAccountRoleService.list(new QueryWrapper<SysAccountRole>().eq("accountId", accountId));
            List<SysRole> roleList = accountRoleList.stream().map(accountRole -> {
                Integer roleId = accountRole.getRoleId();
                SysRole sysRole = sysRoleService.getOne(new QueryWrapper<SysRole>().eq("roleId", roleId));
                return sysRole;
            }).collect(Collectors.toList());
            accountPageVo.setRoleList(roleList);
        });

        // 新page
        Page<AccountPageVo> pageVoPage = new Page<>();
        pageVoPage.setRecords(accountPageVoList);
        pageVoPage.setPages(accountPage.getPages());
        pageVoPage.setCurrent(accountPage.getCurrent());
        pageVoPage.setTotal(accountPage.getTotal());
        pageVoPage.setSize(accountPage.getSize());


        return pageVoPage;
    }

}




