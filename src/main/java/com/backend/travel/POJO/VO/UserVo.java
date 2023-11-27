package com.backend.travel.POJO.VO;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息vo
 */
@Data
public class UserVo implements Serializable {
    private static final long serialVersionUID = 534409537631698335L;
    private Long accountId;
    private Long userId;
    private String userAccount;
    private String username;
    private String userAvatar;
    private String userPhoneNum;
    private String userEmail;
    private String userRole;
    private Date createTime;
    private Integer userStatus;
    private String description;
    private String userRegion;
    private Integer userGender;
}
