package com.backend.travel.POJO.DTO.GuideDto;

import com.backend.travel.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode( callSuper = true )
@Data
public class GuidePageDto extends PageRequest implements Serializable {
    /**
     * 审核状态 0-待审核 1-审核通过 2-审核不通过
     */
    private Integer queryApprovalStatus;

    /**
     * 导游评分
     */
    private Double queryScore;
}
