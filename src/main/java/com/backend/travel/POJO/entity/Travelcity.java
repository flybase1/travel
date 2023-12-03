package com.backend.travel.POJO.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName travelcity
 */
@TableName(value ="travelcity")
@Data
public class Travelcity implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer cityId;

    /**
     * 城市名称
     */
    private String cityName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}