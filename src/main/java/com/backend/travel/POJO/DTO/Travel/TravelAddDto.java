package com.backend.travel.POJO.DTO.Travel;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TravelAddDto implements Serializable {
    private static final long serialVersionUID = -7529826080311239340L;
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

    /**
     * 旅行城市id
     */
    private Long travelCityId;

    /**
     * 开始时间
     */
    private Date beginTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 图片地址
     */
    private String coverPictureUrl;

}
