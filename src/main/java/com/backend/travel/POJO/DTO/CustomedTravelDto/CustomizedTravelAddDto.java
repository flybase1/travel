package com.backend.travel.POJO.DTO.CustomedTravelDto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户定制旅游dto
 * @TableName customizedtravel
 */
@Data
public class CustomizedTravelAddDto implements Serializable {
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

    private static final long serialVersionUID = 1L;
}