package com.backend.travel.controller;

import com.backend.travel.POJO.DTO.SysRoleDto.SysRolePageDto;
import com.backend.travel.POJO.DTO.SysRoleDto.SysRoleSaveDto;
import com.backend.travel.POJO.DTO.SysRoleDto.SysRoleUpdateDto;
import com.backend.travel.POJO.entity.SysRole;
import com.backend.travel.common.BaseResponse;
import com.backend.travel.common.ResultUtils;
import com.backend.travel.service.impl.SysRoleServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping( "/sysRole" )
@RestController
@Api( tags = "权限控制" )
public class SysRoleController {
    @Resource
    private SysRoleServiceImpl sysRoleService;

    /**
     * 列表展示所有权限
     *
     * @return
     */
    @GetMapping( "/listAll" )
    @PreAuthorize( "hasAuthority('system:role:query') " )
    @ApiOperation( value = "列表展示所有权限" )
    public BaseResponse<List<SysRole>> sysRoleList() {
        List<SysRole> list = sysRoleService.list();
        return ResultUtils.success(list);
    }

    /**
     * 分页查询展示
     *
     * @param sysRolePageDto
     * @return
     */
    @PostMapping( "/pageRole" )
    @PreAuthorize( "hasAuthority('system:role:query') " )
    @ApiOperation( value = "分页查询所有权限" )
    public BaseResponse<Page<SysRole>> sysRolePage(@RequestBody SysRolePageDto sysRolePageDto) {
        Page<SysRole> page = sysRoleService.sysRolePage(sysRolePageDto);
        return ResultUtils.success(page);
    }

    /**
     * 保存权限
     *
     * @param sysRoleSaveDto
     * @return
     */
    @PostMapping( "/saveRole" )
    @ApiOperation( value = "新建权限" )
    @PreAuthorize( "hasAuthority('system:role:edit') " + " ||" + "hasAnyAuthority('system:role:add')" )
    public BaseResponse<Boolean> sysRoleSave(@RequestBody SysRoleSaveDto sysRoleSaveDto) {
        Boolean success = sysRoleService.sysRoleSave(sysRoleSaveDto);
        return ResultUtils.success(success);
    }

    /**
     * 更新权限
     *
     * @param sysRoleUpdateDto
     * @return
     */
    @PutMapping( "/updateRole" )
    @ApiOperation( value = "更新权限" )
    @PreAuthorize( "hasAuthority('system:role:edit') " + " ||" + "hasAnyAuthority('system:role:update')" )
    public BaseResponse<Boolean> sysRoleUpdate(@RequestBody SysRoleUpdateDto sysRoleUpdateDto) {
        Boolean success = sysRoleService.sysRoleUpdate(sysRoleUpdateDto);
        return ResultUtils.success(success);
    }

    /**
     * 获取指定权限信息
     *
     * @param roleId
     * @return
     */
    @GetMapping( "/getRoleInfo" )
    @ApiOperation( value = "获取指定id权限" )
    @PreAuthorize( "hasAuthority('system:role:query') " )
    public BaseResponse<SysRole> getRoleInfo(@RequestParam( value = "roleId", required = true ) Integer roleId) {
        SysRole sysRole = sysRoleService.getOne(new QueryWrapper<SysRole>().eq("roleId", roleId));
        return ResultUtils.success(sysRole);
    }


    /**
     * 删除单个数据
     *
     * @param roleId
     * @return
     */
    @DeleteMapping( "/deleteSysRole" )
    @ApiOperation( value = "删除指定id权限" )
    @PreAuthorize( "hasAuthority('system:role:delete') " )
    public BaseResponse<Boolean> deleteSysRole(@RequestParam Long roleId) {
        Boolean deleteSuccess = sysRoleService.deleteSysRoleById(roleId);
        return ResultUtils.success(deleteSuccess);
    }

    /**
     * 批量删除
     *
     * @param roleIds
     * @return
     */
    @DeleteMapping( "/delete/SysRoleList" )
    @ApiOperation( value = "删除权限列表" )
    @PreAuthorize( "hasAuthority('system:role:delete') " )
    public BaseResponse<Boolean> deleteSysRoleList(@RequestBody Integer[] roleIds) {
        Boolean deleteSuccess = sysRoleService.deleteRoleByIds(roleIds);
        return ResultUtils.success(deleteSuccess);
    }


    /**
     * 更改权限对应的菜单
     *
     * @param roleId
     * @param menuIds
     * @return
     */
    @PostMapping( "/updateSysRole/{roleId}" )
    public BaseResponse<Boolean> updateSysRoleMenus(
            @PathVariable Long roleId, @RequestBody Long[] menuIds) {
        Boolean success = sysRoleService.updateSysRoleMenus(roleId, menuIds);
        return ResultUtils.success(success);
    }


}
