package com.backend.travel.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.backend.travel.POJO.DTO.SysRoleDto.SysRolePageDto;
import com.backend.travel.POJO.DTO.SysRoleDto.SysRoleSaveDto;
import com.backend.travel.POJO.DTO.SysRoleDto.SysRoleUpdateDto;
import com.backend.travel.POJO.VO.AccountPageVo;
import com.backend.travel.POJO.entity.Account;
import com.backend.travel.POJO.entity.SysAccountRole;
import com.backend.travel.POJO.entity.SysRoleMenu;
import com.backend.travel.common.CommonConstant;
import com.backend.travel.common.ErrorCode;
import com.backend.travel.execption.BusinessException;
import com.backend.travel.utils.SqlUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.backend.travel.POJO.entity.SysRole;
import com.backend.travel.service.SysRoleService;
import com.backend.travel.dao.SysRoleMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author admin
 * @description 针对表【sys_role(权限表)】的数据库操作Service实现
 * @createDate 2023-11-24 14:26:33
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole>
        implements SysRoleService {
    @Resource
    private SysAccountRoleServiceImpl sysAccountRoleService;
    @Resource
    private SysRoleMenuServiceImpl sysRoleMenuService;

    @Override
    public Page<SysRole> sysRolePage(SysRolePageDto sysRolePageDto) {
        String queryRoleName = sysRolePageDto.getQueryRoleName();
        String queryRoleCode = sysRolePageDto.getQueryRoleCode();
        String queryRemark = sysRolePageDto.getQueryRemark();
        long current = sysRolePageDto.getCurrent();
        long pageSize = sysRolePageDto.getPageSize();
        String sortField = sysRolePageDto.getSortField();
        String sortOrder = sysRolePageDto.getSortOrder();

        // 拼接查询条件
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
        if (queryRoleName != null) {
            queryWrapper.like("roleName", queryRoleName);
        }
        if (queryRoleCode != null) {
            queryWrapper.like("roleCode", queryRoleCode);
        }
        if (queryRemark != null) {
            queryWrapper.like("remark", queryRemark);
        }

        // 排序
        queryWrapper
                .orderBy(SqlUtils.validSortField(sortField),
                        sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                        sortField);

        Page<SysRole> sysRolePage = this.page(new Page<>(current, pageSize), queryWrapper);

        return sysRolePage;
    }

    @Override
    public Boolean sysRoleSave(SysRoleSaveDto sysRoleSaveDto) {
        String roleName = sysRoleSaveDto.getRoleName();
        String roleCode = sysRoleSaveDto.getRoleCode();
        String remark = sysRoleSaveDto.getRemark();
        if (roleName == null || roleName.trim().length() == 0) {
            throw new BusinessException(ErrorCode.PARAMS_EMPTY_ERROR, "权限名不能为空");
        }
        if (roleCode == null || roleCode.trim().length() == 0) {
            throw new BusinessException(ErrorCode.PARAMS_EMPTY_ERROR, "权限标识不能为空");
        }

        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("roleName", roleName);
        queryWrapper.eq("roleCode", roleCode);
        queryWrapper.eq("remark", remark);
        SysRole confirmRoleExist = this.getOne(queryWrapper);
        if (confirmRoleExist != null) {
            throw new BusinessException(ErrorCode.DATA_EXIST_ERROR, "权限名或权限标识已存在");
        }

        SysRole sysRole = new SysRole();
        BeanUtil.copyProperties(sysRoleSaveDto, sysRole);
        boolean save = this.save(sysRole);
        if (!save) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "新建失败");
        }
        return save;
    }

    @Override
    public Boolean sysRoleUpdate(SysRoleUpdateDto sysRoleUpdateDto) {
        Integer roleId = sysRoleUpdateDto.getRoleId();
        String roleName = sysRoleUpdateDto.getRoleName();
        String roleCode = sysRoleUpdateDto.getRoleCode();
        String remark = sysRoleUpdateDto.getRemark();
        if (roleName == null || roleName.trim().length() == 0) {
            throw new BusinessException(ErrorCode.PARAMS_EMPTY_ERROR, "权限名不能为空");
        }
        if (roleCode == null || roleCode.trim().length() == 0) {
            throw new BusinessException(ErrorCode.PARAMS_EMPTY_ERROR, "权限标识不能为空");
        }

        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("roleName", roleName);
        queryWrapper.eq("roleCode", roleCode);
        queryWrapper.eq("remark", remark);
        SysRole sysRole = this.getOne(queryWrapper);
        if (sysRole != null) {
            throw new BusinessException(ErrorCode.DATA_EXIST_ERROR, "权限名或权限标识已存在");
        }
        SysRole updateSysRole = new SysRole();
        BeanUtil.copyProperties(sysRoleUpdateDto, updateSysRole);
        boolean b = this.updateById(updateSysRole);
        if (!b) {
            throw new BusinessException(ErrorCode.DATA_UPDATE_ERROR, "修改数据失败");
        }
        return true;
    }

    @Override
    public Boolean deleteSysRoleById(Long roleId) {
        SysRole sysRole = this.getOne(new QueryWrapper<SysRole>().eq("roleId", roleId));
        if (sysRole.getRoleId() == 2 || sysRole.getRoleId() == 1 || sysRole.getRoleId() == 3) {
            throw new BusinessException(ErrorCode.DATA_FORBIDDEN_WRITE_ERROR, "禁止修改基础权限");
        }
        boolean b = this.removeById(roleId);
        if (!b) {
            throw new BusinessException(ErrorCode.DATA_DELETE_ERROR, "删除数据失败");
        }
        return true;
    }

    @Override
    @Transactional
    public Boolean deleteRoleByIds(Integer[] roleIds) {
        List<Integer> roleIdList = Arrays.asList(roleIds);
        roleIdList.forEach(roleId -> {
            if (roleId == 2 || roleId == 1 || roleId == 3) {
                throw new BusinessException(ErrorCode.DATA_FORBIDDEN_WRITE_ERROR, "禁止修改基础权限");
            }
        });
        // 1. 删除自己表里面id
        boolean b = this.removeByIds(roleIdList);
        if (!b) {
            throw new BusinessException(ErrorCode.DATA_DELETE_ERROR, "删除数据失败");
        }
        // 2.查询sysAccount里面是否存在联系
        List<SysAccountRole> accountRoleList = sysAccountRoleService.list(new QueryWrapper<SysAccountRole>().in("roleId", roleIdList));
        // 3. 判断数据是否为空
        if (accountRoleList.isEmpty()) {
            return true;
        } else {
            boolean remove = sysAccountRoleService.remove(new QueryWrapper<SysAccountRole>().in("roleId", roleIdList));
            if (!remove) {
                throw new BusinessException(ErrorCode.DATA_DELETE_ERROR, "删除数据失败");
            }
        }

        return true;
    }

    @Override
    public Boolean updateSysRoleMenus(Long roleId, Long[] menuIds) {
        // 1.查找menu-role里面是否存在对应的关联
        List<SysRoleMenu> roleMenuList = sysRoleMenuService.list(new QueryWrapper<SysRoleMenu>().eq("roleId", roleId));
        // 2. 不存在关联直接建立联系
        if (!roleMenuList.isEmpty()) {
            // 3. 存在关联，根据roleId清除role-menu所有关系
            boolean remove = sysRoleMenuService.remove(new QueryWrapper<SysRoleMenu>().eq("roleId", roleId));
            if (!remove) {
                throw new BusinessException(ErrorCode.DATA_DELETE_ERROR);
            }
        }
        // 4. 建立新的role-menu关系
        List<SysRoleMenu> newRoleMenuList = new ArrayList<>();
        Arrays.stream(menuIds).forEach(menuId -> {
            SysRoleMenu sysRoleMenu = new SysRoleMenu();
            sysRoleMenu.setRoleId(roleId);
            sysRoleMenu.setMenuId(menuId);
            newRoleMenuList.add(sysRoleMenu);
        });

        boolean b = sysRoleMenuService.saveBatch(newRoleMenuList);
        if (!b) {
            throw new BusinessException(ErrorCode.DATA_INSERT_ERROR);
        }
        return true;
    }
}




