package com.backend.travel.POJO.DTO.TravelTeamDto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 队伍修改表
 * @TableName travel_team
 */
@Data
public class TravelTeamUpdateDto implements Serializable {
    private Long travelTeamId;

    /**
     * 最大人员数量/不包括导游
     */
    private Integer maxNum;

    /**
     * 导游个数
     */
    private Integer guideNum;

    /**
     * 目前加入人员的个数
     */
    private Integer nowNum;

    /**
     * 队伍名
     */
    private String travelTeamName;


    private static final long serialVersionUID = 1L;
}