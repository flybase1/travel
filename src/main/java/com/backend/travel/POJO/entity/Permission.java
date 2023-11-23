package com.backend.travel.POJO.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 权限表
 * @TableName permission
 */
@TableName(value ="permission")
@Data
public class Permission implements Serializable {
    /**
     * 权限id 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer permissionId;

    /**
     * 权限名称
     */
    private String permissionName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}