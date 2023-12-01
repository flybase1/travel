package com.backend.travel.POJO.VO.order;

import cn.hutool.core.date.DateTime;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单分页展示
 */
@Data
public class OrderPageVo implements Serializable {
    private static final long serialVersionUID = 7274464963493348189L;
    /**
     * 主键
     */
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
    private DateTime createTime;

    /**
     * 更新时间
     */
    private DateTime updateTime;

    /**
     * 订单描述
     */
    private String orderDescription;
}
