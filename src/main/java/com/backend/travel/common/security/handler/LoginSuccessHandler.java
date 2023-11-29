package com.backend.travel.common.security.handler;

import java.util.Date;

import cn.hutool.json.JSONUtil;
import com.backend.travel.POJO.VO.UserVo;
import com.backend.travel.POJO.entity.*;
import com.backend.travel.common.ResultUtils;
import com.backend.travel.service.impl.*;
import com.backend.travel.utils.JWTUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 登陆成功处理器
 */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Resource
    private AccountServiceImpl accountService;
    @Resource
    private UserServiceImpl userService;
    @Resource
    private SysRoleServiceImpl sysRoleService;
    @Resource
    private SysRoleMenuServiceImpl sysRoleMenuService;
    @Resource
    private SysAccountRoleServiceImpl sysAccountRoleService;
    @Resource
    private SysMenuServiceImpl sysMenuService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        ServletOutputStream outputStream = response.getOutputStream();
        String userAccount = authentication.getName();
        Account account = accountService.getByUserName(userAccount);
        Long accountId = account.getAccountId();
        User user = userService.getOne(new QueryWrapper<User>().eq("accountId", accountId));

        Integer roleId = account.getPermissionId();

//        List<SysRole> roleList = sysRoleService.list(new QueryWrapper<SysRole>().inSql("id", "SELECT roleId FROM sys_account_role WHERE accountId=" + accountId));
//        Set<SysMenu> menuSet=new HashSet<>();
//        for(SysRole sysRole:roleList){
//            List<SysMenu> sysMenuList = sysMenuService.list(new QueryWrapper<SysMenu>().inSql("id", "SELECT menuId FROM sys_role_menu WHERE roleId=" + sysRole.getRoleId()));
//            for(SysMenu sysMenu:sysMenuList){
//                menuSet.add(sysMenu);
//            }
//        }
//
        List<SysAccountRole> sysAccountRoleList = sysAccountRoleService.list(new QueryWrapper<SysAccountRole>().eq("accountId", accountId));
        List<Integer> roleIdList = sysAccountRoleList.stream().map(sysAccountRole -> {
            Integer sysAccountRoleRoleId = sysAccountRole.getRoleId();
            return sysAccountRoleRoleId;
        }).collect(Collectors.toList());

        List<SysRole> sysRoleList = roleIdList.stream().map((role_Id) -> sysRoleService.getOne(new QueryWrapper<SysRole>().eq("roleId", role_Id))).collect(Collectors.toList());
        Set<SysMenu> menuSet = new HashSet<>();
        for (SysRole sysRole : sysRoleList) {
            Integer sysRoleRoleId = sysRole.getRoleId();
            List<SysMenu> sysMenus = sysMenuService.list(new QueryWrapper<SysMenu>().inSql("id", "select menuId from sys_role_menu where roleId=" + sysRoleRoleId));
            for (SysMenu sysMenu : sysMenus) {
                menuSet.add(sysMenu);
            }
        }


//        SysRole role = sysRoleService.getOne(new QueryWrapper<SysRole>().eq("roleId", roleId));
//        // 1. 查找权限对应的菜单列表
//        List<SysRoleMenu> roleMenuList = sysRoleMenuService.list(new QueryWrapper<SysRoleMenu>().eq("roleId", roleId));
//
//        // 2. 菜单列表查询所有相关信息
//        Set<SysMenu> menuCodeSet = new HashSet<>();
//        roleMenuList.forEach(sysRoleMenu -> {
//            SysMenu sysMenu = sysMenuService.getOne(new QueryWrapper<SysMenu>().eq("id", sysRoleMenu.getMenuId()));
//            menuCodeSet.add(sysMenu);
//        });
        ArrayList<SysMenu> arrayList = new ArrayList<SysMenu>(menuSet);
        // 排序
        arrayList.sort(Comparator.comparing(SysMenu::getOrderNum, Comparator.nullsLast(Comparator.naturalOrder())));
        // 转成菜单树
        List<SysMenu> menuList = sysMenuService.buildTree(arrayList);
        // 3.包装返回用户信息
        UserVo userVo = new UserVo();
        userVo.setAccountId(accountId);
        userVo.setUserId(userVo.getUserId());
        userVo.setUsername(user.getUsername());
        userVo.setUserAvatar(user.getUserAvatar());
        userVo.setUserPhoneNum(account.getUserPhoneNum());
        userVo.setUserEmail(account.getUserEmail());
        userVo.setUserRole(sysRoleList.stream().map(SysRole::getRoleName).collect(Collectors.joining(",")));
        userVo.setCreateTime(user.getCreateTime());
        userVo.setUserStatus(user.getUserStatus());
        userVo.setDescription(user.getUserProfile());
        userVo.setUserRegion(user.getUserRegion());
        userVo.setUserGender(user.getUserGender());
        userVo.setUserAccount(account.getUserAccount());
        String token = JWTUtils.createJWT(userAccount);
        HashMap<String, Object> map = new HashMap<>();
        map.put("authorization", token);
//        map.put("account", account);
        map.put("menuList", menuList);
        map.put("user", userVo);
        // todo 返回权限信息
        outputStream.write(JSONUtil.toJsonStr(ResultUtils.success(map)).getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}
