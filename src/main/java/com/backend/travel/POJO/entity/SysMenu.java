package com.backend.travel.POJO.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName sys_menu
 */
@TableName(value ="sys_menu")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysMenu implements Serializable {
    /**
     * 菜单主键id
     */
    @TableId(type = IdType.AUTO)
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
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;

    @TableField(exist = false)
    private List<SysMenu> children = new ArrayList<>();

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}