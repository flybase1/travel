package com.backend.travel.POJO.DTO.Order;

import com.backend.travel.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 查询订单条件
 */
@EqualsAndHashCode( callSuper = true )
@Data
public class OrderPageQueryDto extends PageRequest implements Serializable {


    private static final long serialVersionUID = -4667226556300849228L;


    /**
     * 账号id
     */
    private Long queryAccountId;

    /**
     * 项目id
     */
    private Long queryTravelId;

    /**
     * 支付方式 0-支付宝 1-微信
     */
    private Integer queryPayUtil;

    /**
     * 订单状态id
     */
    private Integer queryOrderStatusId;

}
