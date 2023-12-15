package com.backend.travel.POJO.DTO.CustomedTravelDto;

import com.backend.travel.common.PageRequest;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户定制旅游
 * @TableName customizedtravel
 */
@EqualsAndHashCode( callSuper = true )
@TableName(value ="customizedtravel")
@Data
public class CustomizedTravelPageDto extends PageRequest implements Serializable {
    /**
     * 主键
     */
    private Long queryCustomizedTravelId;

    /**
     * 旅游性质关联traveltype外键
     */
    private Long queryTravelTypeId;

    /**
     * 旅游城市id，关联外键
     */
    private Long queryTravelCityId;

    /**
     * 用户姓名
     */
    private String queryTravelUserName;

    /**
     * 用户手机号
     */
    private String queryTravelUserPhoneNum;


    private static final long serialVersionUID = 1L;
}