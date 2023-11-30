package com.backend.travel.POJO.VO.guide;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class GuidePageVo implements Serializable {

    /**
     * 用户账号
     */
    private String userAccount;

    /**
     * 导游id
     */
    private Long guideId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 导游证明
     */
    private String guideCertificate;

    /**
     * 审核失败原因
     */
    private String approvalResult;

    /**
     * 审核状态 0-待审核 1-审核通过 2-审核不通过
     */
    private Integer approvalStatus;

    /**
     * 导游评分
     */
    private Double score;

    /**
     * 成为导游时间
     */
    private Date createTime;

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

    private static final long serialVersionUID = 1L;
}
