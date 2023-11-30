package com.backend.travel.POJO.VO.user;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserVo implements Serializable {
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


}
