package com.backend.travel.POJO.DTO.TravelTeamDto;

import com.backend.travel.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode( callSuper = true )
@Data
public class TravelTeamPageDto extends PageRequest implements Serializable {

    private static final long serialVersionUID = -592260685756807543L;
    /**
     * 创建项目队伍id
     */
    private Long queryTravelTeamId;

    /**
     * 项目id
     */
    private Long queryTravelId;

    /**
     *
     */
    private String queryTravelTitle;

    /**
     * 创建旅游队伍账号的id
     */
    private Long queryCreateTeamAccountId;
    /**
     * 账号
     */
    private String queryUserAccount;

}
