package com.backend.travel.POJO.DTO;

import com.backend.travel.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 查询账号信息
 */
@EqualsAndHashCode( callSuper = true )
@Data
public class AccountPageDto extends PageRequest {
    // 查询账号
    private String queryAccount;
    // 查询手机号码
    private String queryPhoneNum;
    // 查询邮箱
    private String queryEmail;
    // 查询权限
    private Integer queryRoleId;
    // 查询状态
    private Integer queryStatus;
}