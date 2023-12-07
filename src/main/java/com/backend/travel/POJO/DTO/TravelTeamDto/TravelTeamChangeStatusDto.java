package com.backend.travel.POJO.DTO.TravelTeamDto;

import lombok.Data;

import java.io.Serializable;

/**
 * 队伍修改表
 *
 * @TableName travel_team
 */
@Data
public class TravelTeamChangeStatusDto implements Serializable {
    private Long travelTeamId;

    private Integer traveTeamStatus;

    private static final long serialVersionUID = 1L;
}