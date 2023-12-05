package com.backend.travel.POJO.DTO.Travel;

import com.backend.travel.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode( callSuper = true )
@Data
public class TravelPageDto extends PageRequest implements Serializable {
    /**
     * 项目id
     */
    private Long queryTravelId;

    /**
     * 创建者账号id
     */
    private Long queryAccountId;

    /**
     * 旅游标题
     */
    private String queryTravelTitle;

    /**
     * 项目类型id
     */
    private Integer queryTypeId;

    /**
     * 旅行城市id
     */
    private Long queryTravelCityId;

    /**
     * 项目评分 0-5
     */
    private Double queryTravelScore;

    /**
     * 项目状态 0-未开始 1-已经开始 2-结束
     */
    private Integer queryTravelStatusId;

    /**
     * 开始时间
     */
    private Date queryBeginTime;

    /**
     * 封面地址
     */
    private String coverPictureUrl;


    private static final long serialVersionUID = -7802876218671592275L;
}
