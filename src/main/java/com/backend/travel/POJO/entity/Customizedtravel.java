package com.backend.travel.POJO.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户定制旅游
 * @TableName customizedtravel
 */
@TableName(value ="customizedtravel")
@Data
public class Customizedtravel implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long customizedTravelId;

    /**
     * 旅游性质关联traveltype外键
     */
    private Long travelTypeId;

    /**
     * 旅游城市id，关联外键
     */
    private Long travelCityId;

    /**
     * 开始时间
     */
    private Date travelBeginTime;

    /**
     * 结束时间
     */
    private Date travelEndTime;

    /**
     * 旅行人数
     */
    private Integer travelNumbers;

    /**
     * 预估费用
     */
    private Integer travelEstimation;

    /**
     * 实际费用
     */
    private Integer travelMoney;

    /**
     * 期望导游数
     */
    private Integer travelGuideNums;

    /**
     * 期望需求
     */
    private String travelDescription;

    /**
     * 用户姓名
     */
    private String travelUserName;

    /**
     * 用户手机号
     */
    private String travelUserPhoneNum;

    private Long accountId;

    private Integer travelStatus;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}