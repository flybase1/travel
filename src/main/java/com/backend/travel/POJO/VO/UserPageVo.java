package com.backend.travel.POJO.VO;

import com.backend.travel.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode( callSuper = true )
@Data
public class UserPageVo extends PageRequest {
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 账号id
     */
    private Long accountId;

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
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 用户账号
     */
    private String userAccount;

    /**
     * 用户手机号
     */
    private String userPhoneNum;

    /**
     * 用户邮箱
     */
    private String userEmail;

}
