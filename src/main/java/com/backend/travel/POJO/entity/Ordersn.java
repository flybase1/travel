package com.backend.travel.POJO.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName ordersn
 */
@TableName(value ="ordersn")
@Data
public class Ordersn implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long orderId;

    /**
     * 账号id
     */
    private Long accountId;

    /**
     * 项目id
     */
    private Long travelId;

    /**
     * 总金额
     */
    private Integer totalAmount;

    /**
     * 阿里订单号
     */
    private String alipayTradeNo;

    /**
     * 支付方式 0-支付宝 1-微信
     */
    private Integer payUtil;

    /**
     * 订单状态id
     */
    private Integer orderStatusId;

    /**
     * 是否删除 0-是 1-否
     */
    private Integer isDelete;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 订单描述
     */
    private String orderDescription;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}