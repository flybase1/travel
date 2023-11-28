package com.backend.travel.POJO.DTO.AccountDto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AccountInfoVo implements Serializable {

    private static final long serialVersionUID = 5390993725395281443L;
    private Long accountId;

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

    /**
     * 用户角色 0-用户 1-导游 2-管理员
     */
    private Integer permissionId;

    /**
     * 账号状态 0-正常 1-封禁
     */
    private Integer accountStatus;
}
