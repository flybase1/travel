package com.backend.travel.POJO.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName orderstatus
 */
@TableName(value ="orderstatus")
@Data
public class Orderstatus implements Serializable {
    /**
     * 订单状态id
     */
    @TableId(type = IdType.AUTO)
    private Integer orderStatusId;

    /**
     * 订单状态名称
     */
    private String orderStatusName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}