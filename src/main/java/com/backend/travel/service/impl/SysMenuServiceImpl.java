package com.backend.travel.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.backend.travel.POJO.DTO.SysMenuDto.SysMenuAddDto;
import com.backend.travel.POJO.DTO.SysMenuDto.SysMenuUpdateDto;
import com.backend.travel.POJO.entity.SysAccountRole;
import com.backend.travel.POJO.entity.SysRoleMenu;
import com.backend.travel.common.ErrorCode;
import com.backend.travel.execption.BusinessException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.backend.travel.POJO.entity.SysMenu;
import com.backend.travel.service.SysMenuService;
import com.backend.travel.dao.SysMenuMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author admin
 * @description 针对表【sys_menu】的数据库操作Service实现
 * @createDate 2023-11-24 14:26:33
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu>
        implements SysMenuService {
    @Resource
    private SysRoleMenuServiceImpl sysRoleMenuService;

    @Override
    public List<SysMenu> buildTree(List<SysMenu> arrayList) {
        List<SysMenu> sysMenus = new ArrayList<>();

        // 构建树
        arrayList.forEach(sysMenu -> {
            // 寻找子节点
            arrayList.forEach(sysMenu1 -> {
                if (sysMenu1.getParentId() == sysMenu.getId()) {
                    sysMenu.getChildren().add(sysMenu1);
                }
            });
            if (sysMenu.getParentId() == 0) {
                sysMenus.add(sysMenu);
            }
        });

        return sysMenus;
    }

    @Override
    public List<SysMenu> treeListAllSysMenu() {
        List<SysMenu> sysMenuList = this.list(new QueryWrapper<SysMenu>().orderByAsc("orderNum"));
        List<SysMenu> sysMenus = buildTree(sysMenuList);
        return sysMenus;
    }

    @Override
    public Boolean saveMenutoDataBase(SysMenuAddDto sysMenuAddDto) {
        String menuName = sysMenuAddDto.getMenuName();
        String icon = sysMenuAddDto.getIcon();
        Long parentId = sysMenuAddDto.getParentId();
        Integer orderNum = sysMenuAddDto.getOrderNum();
        String menuPath = sysMenuAddDto.getMenuPath();
        String component = sysMenuAddDto.getComponent();
        String menuType = sysMenuAddDto.getMenuType();
        String perms = sysMenuAddDto.getPerms();
        String remark = sysMenuAddDto.getRemark();

        SysMenu sysMenu = new SysMenu();
        BeanUtil.copyProperties(sysMenuAddDto, sysMenu);
        boolean save = this.save(sysMenu);
        if (!save) {
            throw new BusinessException(ErrorCode.DATA_INSERT_ERROR);
        }

        return true;
    }

    @Override
    public Boolean updateMenu(SysMenuUpdateDto sysMenuUpdateDto) {
        Long id = sysMenuUpdateDto.getId();
        String menuName = sysMenuUpdateDto.getMenuName();
        String icon = sysMenuUpdateDto.getIcon();
        Long parentId = sysMenuUpdateDto.getParentId();
        Integer orderNum = sysMenuUpdateDto.getOrderNum();
        String menuPath = sysMenuUpdateDto.getMenuPath();
        String component = sysMenuUpdateDto.getComponent();
        String menuType = sysMenuUpdateDto.getMenuType();
        String perms = sysMenuUpdateDto.getPerms();
        String remark = sysMenuUpdateDto.getRemark();

        SysMenu sysMenu = new SysMenu();
        BeanUtil.copyProperties(sysMenuUpdateDto, sysMenu);
        boolean b = this.updateById(sysMenu);
        if (!b) {
            throw new BusinessException(ErrorCode.DATA_UPDATE_ERROR);
        }

        return true;
    }

    @Override
    @Transactional
    public Boolean deleteMenu(Long menuId) {
        // 如果有父节点，需要先删除子节点,计算当前数据里面是否有对应的父节点
        long countParent = this.count(new QueryWrapper<SysMenu>().eq("parentId", menuId));
        if (countParent > 0) {
            throw new BusinessException(ErrorCode.DATA_DELETE_ERROR, "仍然还有子节点，需要删除子菜单");
        }
        boolean b = this.removeById(menuId);
        if (!b) {
            throw new BusinessException(ErrorCode.DATA_DELETE_ERROR);
        }
        List<SysRoleMenu> sysRoleMenuList = sysRoleMenuService.list(new QueryWrapper<SysRoleMenu>().eq("menuId", menuId));
        if (sysRoleMenuList.size() > 0) {
            List<Long> sysAccountRoleIds = sysRoleMenuList.stream().map(sysAccountRole -> sysAccountRole.getId()).collect(Collectors.toList());
            boolean removeBatchByIds = sysRoleMenuService.removeBatchByIds(sysAccountRoleIds);
            if (!removeBatchByIds) {
                throw new BusinessException(ErrorCode.DATA_DELETE_ERROR, "删除权限-菜单关系失败");
            }
        }
        return true;
    }
}




