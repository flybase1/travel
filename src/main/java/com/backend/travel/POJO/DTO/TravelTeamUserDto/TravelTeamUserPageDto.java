package com.backend.travel.POJO.DTO.TravelTeamUserDto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TravelTeamUserPageDto implements Serializable {

    private static final long serialVersionUID = 7038457243927055398L;
    private Long queryTeamId;

    /**
     * 关联旅游队伍主键
     */
    private Long queryTravelTeamId;

    /**
     * 加入用户的id
     */
    private Long queryJoinAccountId;

    /**
     * 是否结束
     */
    private Integer isFinished;

    /**
     * 是否是导游 0-用户 1-导游
     */
    private Integer isGuide;
}
