package com.backend.travel.POJO.VO.user;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserPageVo implements Serializable {

    private static final long serialVersionUID = -7061485373040471721L;
    private Long userId;

    /**
     * 账号id
     */
    private Long accountId;

    private String userAccount;

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
    private Integer isDeleted;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建时间
     */
    private Date createTime;

}
