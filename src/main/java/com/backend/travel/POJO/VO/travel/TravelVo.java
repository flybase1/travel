package com.backend.travel.POJO.VO.travel;

import com.backend.travel.POJO.entity.Picture;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class TravelVo implements Serializable {

    private Long travelId;

    /**
     * 创建者账号id
     */
    private Long accountId;

    /**
     * 旅游人数
     */
    private Integer travelNumbers;

    /**
     * 旅游标题
     */
    private String travelTitle;

    /**
     * 旅行内容描述
     */
    private String travelDescription;

    /**
     * 项目价格
     */
    private Integer travelPrice;

    /**
     * 项目类型id
     */
    private Integer typeId;
    private String typeName;

    /**
     * 旅行城市id
     */
    private Long travelCityId;
    private String travelCityName;

    private List<Picture> pictureList;
    /**
     * 项目评分 0-5
     */
    private Double travelScore;

    /**
     * 项目状态 0-未开始 1-已经开始 2-结束
     */
    private Integer travelStatusId;

    /**
     * 开始时间
     */
    private Date beginTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 项目状态
     */
    private Integer projectStatus;
}
