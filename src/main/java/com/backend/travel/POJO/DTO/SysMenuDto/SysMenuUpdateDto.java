package com.backend.travel.POJO.DTO.SysMenuDto;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysMenuUpdateDto implements Serializable {
    private Long id;
    /**
     * 菜单名字
     */
    private String menuName;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 父级菜单id
     */
    private Long parentId;

    /**
     * 显示顺序
     */
    private Integer orderNum;

    /**
     * 路由地址
     */
    private String menuPath;

    /**
     * 路由组件
     */
    private String component;

    /**
     * 菜单类型 M目录 C菜单 F按钮
     */
    private String menuType;

    /**
     * 权限标识
     */
    private String perms;

    /**
     * 备注
     */
    private String remark;

}
