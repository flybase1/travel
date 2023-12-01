package com.backend.travel.POJO.DTO.Order;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class OrderUpdateDto implements Serializable {

    private static final long serialVersionUID = 4836618459987135714L;

    private Long orderId;

    /**
     * 总金额
     */
    private Integer totalAmount;

    /**
     * 支付方式 0-支付宝 1-微信
     */
    private Integer payUtil;

    /**
     * 订单状态id
     */
    private Integer orderStatusId;
    /**
     * 订单描述
     */
    private String orderDescription;
}
