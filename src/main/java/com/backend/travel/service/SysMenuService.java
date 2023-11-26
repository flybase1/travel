package com.backend.travel.service;

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
     * @param arrayList
     * @return
     */
    List<SysMenu> buildTree(ArrayList<SysMenu> arrayList);
}
