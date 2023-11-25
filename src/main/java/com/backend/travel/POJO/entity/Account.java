package com.backend.travel.POJO.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.Data;

/**
 * 账号表
 *
 * @TableName account
 */
@TableName( value = "account" )
@Data
public class Account implements Serializable {
    /**
     * 账号id
     */
    @TableId( type = IdType.AUTO )
    private Long accountId;

    /**
     * 用户账号
     */
    private String userAccount;

    /**
     * 用户密码
     */
    private String userPassword;

    /**
     * 用户手机号
     */
    private String userPhoneNum;

    /**
     * 用户邮箱
     */
    private String userEmail;

    /**
     * 微信id
     */
    private String unionId;

    /**
     * 自己的微信公众号id
     */
    private String mpOpenId;

    /**
     * 用户角色 0-用户 1-导游 2-管理员
     */
    private Integer permissionId;

    /**
     * 账号状态 0-正常 1-封禁
     */
    private Integer accountStatus;

    @TableField( exist = false )
    private static final long serialVersionUID = 1L;
}