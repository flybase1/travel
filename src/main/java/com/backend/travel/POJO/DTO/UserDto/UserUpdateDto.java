package com.backend.travel.POJO.DTO.UserDto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserUpdateDto implements Serializable {

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


    private static final long serialVersionUID = 1L;

}
