package com.backend.travel.POJO.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 权限管理表
 * @TableName sys_account_role
 */
@TableName(value ="sys_account_role")
@Data
public class SysAccountRole implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    private Long accountId;

    /**
     * 权限id
     */
    private Integer roleId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}