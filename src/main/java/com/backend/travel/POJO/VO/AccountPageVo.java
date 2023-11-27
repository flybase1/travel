package com.backend.travel.POJO.VO;

import com.backend.travel.POJO.entity.SysRole;
import com.backend.travel.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@Data
public class AccountPageVo implements Serializable {
    private static final long serialVersionUID = 624407348661005535L;
    /**
     * id
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
     * 微信id
     */
    private String unionId;

    /**
     * 自己的微信公众号id
     */
    private String mpOpenId;

    /**
     * 用户角色 0-用户 1-导游 2-管理员
     */
    private Integer permissionId;

    /**
     * 展示权限列表
     */
    private List<SysRole> roleList;

    /**
     * 账号状态 0-正常 1-封禁
     */
    private Integer accountStatus;
}
