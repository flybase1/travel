package com.backend.travel.service;

import com.backend.travel.POJO.DTO.SysMenuDto.SysMenuAddDto;
import com.backend.travel.POJO.DTO.SysMenuDto.SysMenuUpdateDto;
import com.backend.travel.POJO.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author admin
 * @description 针对表【sys_menu】的数据库操作Service
 * @createDate 2023-11-24 14:26:33
 */
public interface SysMenuService extends IService<SysMenu> {

    /**
     * 构建树形结构
     *
     * @param arrayList
     * @return
     */
    List<SysMenu> buildTree(List<SysMenu> arrayList);

    /**
     * 返回所有数据
     *
     * @return
     */
    List<SysMenu> treeListAllSysMenu();

    /**
     * 新建菜单
     *
     * @param sysMenuAddDto
     * @return
     */
    Boolean saveMenutoDataBase(SysMenuAddDto sysMenuAddDto);

    /**
     * 更新菜单
     *
     * @param sysMenuUpdateDto
     * @return
     */
    Boolean updateMenu(SysMenuUpdateDto sysMenuUpdateDto);


    /**
     * 删除菜单
     *
     * @param menuId
     * @return
     */
    Boolean deleteMenu(Long menuId);
}
