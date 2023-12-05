package com.backend.travel.POJO.DTO.UserDto;

import lombok.Data;

import java.io.Serializable;

/**
 * 更换头像
 */
@Data
public class UserAvatarUpdate implements Serializable {

    private static final long serialVersionUID = 1491239263780906701L;

    private Long userId;

    private String userAvatar;
}
