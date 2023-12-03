package com.backend.travel.POJO.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 旅行项目
 * @TableName traveltype
 */
@TableName(value ="traveltype")
@Data
public class Traveltype implements Serializable {
    /**
     * 类型id
     */
    @TableId(type = IdType.AUTO)
    private Integer typeId;

    /**
     * 1-团队游 2-个人 3-亲子游
     */
    private String travelType;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}