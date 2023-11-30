package com.backend.travel.POJO.VO.guide;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class GuideUpdateVo implements Serializable {

    /**
     * 导游id
     */
    private Long guideId;

    /**
     * 导游证明
     */
    private String guideCertificate;

    /**
     * 审核状态 0-待审核 1-审核通过 2-审核不通过
     */
    private Integer approvalStatus;

    /**
     * 导游评分
     */
    private Double score;

    /**
     * 审核失败原因
     */
    private String approvalResult;

}
