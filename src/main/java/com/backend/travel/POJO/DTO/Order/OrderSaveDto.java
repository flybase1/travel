package com.backend.travel.POJO.DTO.Order;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class OrderSaveDto implements Serializable {

    private static final long serialVersionUID = 4836618459987135714L;

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
