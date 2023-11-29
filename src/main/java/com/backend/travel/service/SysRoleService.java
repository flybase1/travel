package com.backend.travel.service;

import com.backend.travel.POJO.DTO.SysRoleDto.SysRolePageDto;
import com.backend.travel.POJO.DTO.SysRoleDto.SysRoleSaveDto;
import com.backend.travel.POJO.DTO.SysRoleDto.SysRoleUpdateDto;
import com.backend.travel.POJO.entity.SysRole;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author admin
 * @description 针对表【sys_role(权限表)】的数据库操作Service
 * @createDate 2023-11-24 14:26:33
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * 分页查询展示
     *
     * @param sysRolePageDto
     * @return
     */
    Page<SysRole> sysRolePage(SysRolePageDto sysRolePageDto);

    /**
     * 创建权限
     *
     * @param sysRoleSaveDto
     * @return
     */
    Boolean sysRoleSave(SysRoleSaveDto sysRoleSaveDto);

    /**
     * 修改权限
     *
     * @param sysRoleUpdateDto
     * @return
     */
    Boolean sysRoleUpdate(SysRoleUpdateDto sysRoleUpdateDto);

    /**
     * 删除单个权限
     *
     * @param roleId
     * @return
     */
    Boolean deleteSysRoleById(Long roleId);

    /**
     * 删除多个权限
     *
     * @param roleIds
     * @return
     */
    Boolean deleteRoleByIds(Integer[] roleIds);

    /**
     * 更新权限对应的菜单
     * @param roleId
     * @param menuIds
     * @return
     */
    Boolean updateSysRoleMenus(Long roleId, Long[] menuIds);
}
