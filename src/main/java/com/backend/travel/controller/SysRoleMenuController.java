package com.backend.travel.controller;

import com.backend.travel.POJO.entity.SysRoleMenu;
import com.backend.travel.common.BaseResponse;
import com.backend.travel.common.ResultUtils;
import com.backend.travel.service.impl.SysRoleMenuServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping( "/roleMenu" )
@RestController
public class SysRoleMenuController {
    @Resource
    private SysRoleMenuServiceImpl sysRoleMenuService;

    /**
     * 获取当前roleId所有的对应菜单id集合
     *
     * @param roleId
     * @return
     */
    @GetMapping( "/menus" )
    public BaseResponse<List<Long>> listRolesMenu(@RequestParam Integer roleId) {
        List<SysRoleMenu> roleMenuList = sysRoleMenuService.list(new QueryWrapper<SysRoleMenu>().eq("roleId", roleId));
        List<Long> menuIds = roleMenuList.stream().map(roleMenu -> {
            return roleMenu.getMenuId();
        }).collect(Collectors.toList());
        return ResultUtils.success(menuIds);
    }
}
