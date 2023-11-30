package com.backend.travel.POJO.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 导游表
 * @TableName guide
 */
@TableName(value ="guide")
@Data
public class Guide implements Serializable {
    /**
     * 导游id
     */
    @TableId(type = IdType.AUTO)
    private Long guideId;

    /**
     * 用户id 关联用户表
     */
    private Long userId;

    /**
     * 导游证明
     */
    private String guideCertificate;

    /**
     * 审核状态 0-待审核 1-审核通过 2-审核不通过
     */
    private Integer approvalStatus;

    /**
     * 审核失败原因
     */
    private String approvalResult;
    /**
     * 导游评分
     */
    private Double score;

    /**
     * 创建时间
     */
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}