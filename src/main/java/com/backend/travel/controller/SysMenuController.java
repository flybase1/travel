package com.backend.travel.controller;

import com.backend.travel.POJO.DTO.SysMenuDto.SysMenuAddDto;
import com.backend.travel.POJO.DTO.SysMenuDto.SysMenuUpdateDto;
import com.backend.travel.POJO.entity.SysMenu;
import com.backend.travel.common.BaseResponse;
import com.backend.travel.common.ResultUtils;
import com.backend.travel.service.impl.SysMenuServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping( "/sysMenu" )
@RestController
public class SysMenuController {
    @Resource
    private SysMenuServiceImpl sysMenuService;

    /**
     * 构建树形结构
     *
     * @return
     */
    @GetMapping( "/treeList" )
    @PreAuthorize( "hasAuthority('system:menu:query')" )
    public BaseResponse<List<SysMenu>> treeListAllSysMenu() {
        List<SysMenu> sysMenuList = sysMenuService.treeListAllSysMenu();
        return ResultUtils.success(sysMenuList);
    }

    /**
     * 更新菜单
     *
     * @param sysMenuAddDto
     * @return
     */
    @PostMapping( "/saveMenu" )
    public BaseResponse<Boolean> saveMenu(@RequestBody SysMenuAddDto sysMenuAddDto) {
        Boolean success = sysMenuService.saveMenutoDataBase(sysMenuAddDto);
        return ResultUtils.success(success);
    }

    /**
     * 修改菜单
     *
     * @param sysMenuUpdateDto
     * @return
     */
    @PutMapping( "/updateMenu" )
    public BaseResponse<Boolean> updateMenu(@RequestBody SysMenuUpdateDto sysMenuUpdateDto) {
        Boolean success = sysMenuService.updateMenu(sysMenuUpdateDto);
        return ResultUtils.success(success);
    }

    /**
     * 根据id删除菜单
     *
     * @param menuId
     * @return
     */
    @DeleteMapping( "/deleteMenu/{menuId}" )
    public BaseResponse<Boolean> deleteMenu(@PathVariable Long menuId ) {
        Boolean success = sysMenuService.deleteMenu(menuId);
        return ResultUtils.success(success);
    }

    /**
     * 获取指定id的menu信息
     *
     * @param menuId
     * @return
     */
    @GetMapping( "/getMenuInfo" )
    public BaseResponse<SysMenu> getMenuInfo(@RequestParam( "menuId" ) Long menuId) {
        SysMenu sysMenu = sysMenuService.getOne(new QueryWrapper<SysMenu>().eq("id", menuId));
        return ResultUtils.success(sysMenu);
    }
}
