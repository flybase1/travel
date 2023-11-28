package com.backend.travel.POJO.DTO.AccountDto;

import lombok.Data;

import java.io.Serializable;

/**
 * 修改账号信息
 */
@Data
public class AccountUpdateDto implements Serializable {

    private static final long serialVersionUID = -7440031844497291856L;

    /**
     * 账号id
     */
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
     * 帐号状态
     */
    private Integer accountStatus;

}
