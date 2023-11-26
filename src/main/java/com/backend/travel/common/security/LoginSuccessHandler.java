package com.backend.travel.common.security;

import cn.hutool.json.JSONUtil;
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
        // 1. 查找权限对应的菜单列表
        List<SysRoleMenu> roleMenuList = sysRoleMenuService.list(new QueryWrapper<SysRoleMenu>().eq("roleId", roleId));
        // 2. 菜单列表查询所有相关信息
        Set<SysMenu> menuCodeSet = new HashSet<>();
        roleMenuList.forEach(sysRoleMenu -> {
            SysMenu sysMenu = sysMenuService.getOne(new QueryWrapper<SysMenu>().eq("id", sysRoleMenu.getMenuId()));
            menuCodeSet.add(sysMenu);
        });
        ArrayList<SysMenu> arrayList = new ArrayList<SysMenu>(menuCodeSet);
        // 排序
        arrayList.sort(Comparator.comparing(SysMenu::getOrderNum, Comparator.nullsLast(Comparator.naturalOrder())));
        // 转成菜单树
        List<SysMenu> menuList = sysMenuService.buildTree(arrayList);
        // 3.

//        QueryWrapper<SysAccountRole> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("accountId", accountId);
//        List<SysAccountRole> sysAccountRoleList = sysAccountRoleService.list(queryWrapper);
//        // 1.2 匹配当前账号所有的权限信息
//        List<SysRole> sysRoleList = sysAccountRoleList.stream().map(sysAccountRole -> {
//            Integer roleId = sysAccountRole.getRoleId();
//            return sysRoleService.getOne(new QueryWrapper<SysRole>().eq("roleId", roleId));
//        }).collect(Collectors.toList());
//        // 2. 遍历所有角色，获取所有的菜单权限,不重复
//        Set<SysMenu> menuCodeSet = new HashSet<>();
//        for (SysRole sysRole : sysRoleList) {
//            // 遍历角色菜单表，找到所有对应的菜单id
//            List<SysRoleMenu> sysRoleMenus = sysRoleMenuService.list(new QueryWrapper<SysRoleMenu>().eq("roleId", sysRole.getRoleId()));
//            // 遍历所有菜单id，找到所有对应的菜单信息
//            sysRoleMenus.forEach(sysRoleMenu -> {
//                SysMenu sysMenu = sysMenuService.getOne(new QueryWrapper<SysMenu>().eq("id", sysRoleMenu.getMenuId()));
//                // 去重
//                menuCodeSet.add(sysMenu);
//            });
//        }
//        ArrayList<SysMenu> arrayList = new ArrayList<SysMenu>(menuCodeSet);
//        // 排序
//        arrayList.sort(Comparator.comparing(SysMenu::getOrderNum, Comparator.nullsLast(Comparator.naturalOrder())));
//        // 转成菜单树
//        List<SysMenu> menuList = sysMenuService.buildTree(arrayList);

        String token = JWTUtils.createJWT(userAccount);
        HashMap<String, Object> map = new HashMap<>();
        map.put("authorization", token);
        map.put("account", account);
        map.put("menuList", menuList);
        map.put("user", user);
        // todo 返回权限信息
        outputStream.write(JSONUtil.toJsonStr(ResultUtils.success(map)).getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}
