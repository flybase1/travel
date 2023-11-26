package com.backend.travel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.backend.travel.POJO.entity.SysMenu;
import com.backend.travel.service.SysMenuService;
import com.backend.travel.dao.SysMenuMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author admin
 * @description 针对表【sys_menu】的数据库操作Service实现
 * @createDate 2023-11-24 14:26:33
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu>
        implements SysMenuService {

    @Override
    public List<SysMenu> buildTree(ArrayList<SysMenu> arrayList) {
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
}




