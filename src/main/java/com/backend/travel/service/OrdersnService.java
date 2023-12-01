package com.backend.travel.service;

import com.backend.travel.POJO.DTO.Order.OrderPageQueryDto;
import com.backend.travel.POJO.DTO.Order.OrderSaveDto;
import com.backend.travel.POJO.DTO.Order.OrderUpdateDto;
import com.backend.travel.POJO.VO.order.OrderPageVo;
import com.backend.travel.POJO.VO.order.OrderVo;
import com.backend.travel.POJO.entity.Ordersn;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author admin
* @description 针对表【ordersn】的数据库操作Service
* @createDate 2023-12-01 14:59:07
*/
public interface OrdersnService extends IService<Ordersn> {

    /**
     * 分页展示订单信息
     * @param orderPageQueryDto
     * @return
     */
    Page<OrderPageVo> pageOrder(OrderPageQueryDto orderPageQueryDto);

    /**
     * 获取订单信息
     * @param orderId
     * @return
     */
    OrderVo getOrderInfo(Long orderId);

    /**
     * 订单保存
     * @param orderSaveDto
     * @return
     */
    Boolean saveOrder(OrderSaveDto orderSaveDto);

    /**
     * 订单更新
     * @param orderUpdateDto
     * @return
     */
    Boolean updateOrder(OrderUpdateDto orderUpdateDto);

    /**
     * 删除指定的订单
     * @param orderId
     * @return
     */
    Boolean deleteOrderById(Long orderId);


    /**
     * 批量删除
     * @param orderIds
     * @return
     */
    Boolean deleteBathOrderByIds(Long[] orderIds);
}
