package com.backend.travel.POJO.VO.travelTeamUser;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TravelTeamUserVo implements Serializable {

    private static final long serialVersionUID = 7038457243927055398L;
    private Long travelTeamUserId;

    /**
     * 关联旅游队伍主键
     */
    private Long travelTeamId;

    private String travelTeamName;

    /**
     * 加入用户的id
     */
    private Long joinAccountId;

    private String userAccount;


    /**
     * 用户加入时间
     */
    private Date joinTime;

    /**
     * 是否结束
     */
    private Integer isFinished;

    /**
     * 是否是导游 0-用户 1-导游
     */
    private Integer isGuide;
}
