package com.backend.travel.POJO.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName travelstatus
 */
@TableName(value ="travelstatus")
@Data
public class Travelstatus implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer travelStatusId;

    /**
     * 旅行项目状态
     */
    private String travelStatusName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}