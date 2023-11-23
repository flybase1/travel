package com.backend.travel.POJO.VO;

import lombok.Data;

import java.io.Serializable;

@Data
public class AccountVo implements Serializable {

    private Long accountId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户账号
     */
    private String userAccount;

    /**
     * 用户角色  1-admin 2-user 3-guide
     */
    private Integer permissionId;
}
