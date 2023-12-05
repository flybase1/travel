package com.backend.travel.POJO.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 旅游表
 * @TableName travel
 */
@TableName(value ="travel")
@Data
public class Travel implements Serializable {
    /**
     * 旅游表
     */
    @TableId(type = IdType.AUTO)
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

    /**
     * 旅行城市id
     */
    private Long travelCityId;

    /**
     * 项目评分 0-5
     */
    private Double travelScore;

    /**
     * 项目状态 0-未开始 1-已经开始 2-结束，外键
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
     * 是否删除
     */
    @TableLogic
    private Integer isDeleted;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 项目状态 0-未发布 1-发布 2-删除
     */
    private Integer projectStatus;

    /**
     * 封面图片
     */
    private String coverPictureUrl;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}