package com.backend.travel.POJO.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户表
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * 用户id
     */
    @TableId(type = IdType.AUTO)
    private Long userId;

    /**
     * 用户昵称
     */
    private String username;

    /**
     * 用户头像链接地址
     */
    private String userAvatar;

    /**
     * 用户年龄
     */
    private Integer userAge;

    /**
     * 用户地区
     */
    private String userRegion;

    /**
     * 个人介绍
     */
    private String userProfile;

    /**
     * 用户性别 0-男 1-女
     */
    private Integer userGender;

    /**
     * 用户状态 0-正常 1-封禁
     */
    private Integer userStatus;

    /**
     * 是否删除 0-是 1-否
     */
    @TableLogic
    private Integer isDeleted;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建时间
     */
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}