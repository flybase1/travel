package com.backend.travel.controller;

import com.backend.travel.POJO.DTO.Order.OrderPageQueryDto;
import com.backend.travel.POJO.DTO.Order.OrderSaveDto;
import com.backend.travel.POJO.DTO.Order.OrderUpdateDto;
import com.backend.travel.POJO.VO.order.OrderPageVo;
import com.backend.travel.POJO.VO.order.OrderVo;
import com.backend.travel.common.BaseResponse;
import com.backend.travel.common.ResultUtils;
import com.backend.travel.service.impl.OrdersnServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RequestMapping( "/order" )
@RestController
public class OrderController {
    @Resource
    private OrdersnServiceImpl ordersnService;

    /**
     * 分页展示订单信息
     *
     * @param orderPageQueryDto
     * @return
     */
    @PostMapping( "/pageOrder" )
    public BaseResponse<Page<OrderPageVo>> pageOrder(@RequestBody OrderPageQueryDto orderPageQueryDto) {
        Page<OrderPageVo> orderPageVoPage = ordersnService.pageOrder(orderPageQueryDto);
        return ResultUtils.success(orderPageVoPage);
    }

    /**
     * 根据id获取订单信息
     *
     * @param orderId
     * @return
     */
    @GetMapping( "/getOrder" )
    public BaseResponse<OrderVo> getOrderInfo(@RequestParam Long orderId) {
        OrderVo orderVo = ordersnService.getOrderInfo(orderId);
        return ResultUtils.success(orderVo);
    }

    /**
     * 添加订单
     *
     * @param orderSaveDto
     * @return
     */
    @PostMapping( "/addOrder" )
    public BaseResponse<Boolean> addOrder(@RequestBody OrderSaveDto orderSaveDto) {
        Boolean success = ordersnService.saveOrder(orderSaveDto);
        return ResultUtils.success(success);
    }


    /**
     * 更新订单
     *
     * @param orderUpdateDto
     * @return
     */
    @PutMapping( "/updateOrder" )
    public BaseResponse<Boolean> updateOrder(@RequestBody OrderUpdateDto orderUpdateDto) {
        Boolean success = ordersnService.updateOrder(orderUpdateDto);
        return ResultUtils.success(success);
    }

    /**
     * 删除指定订单
     *
     * @param orderId
     * @return
     */
    @DeleteMapping( "/deleteOrder" )
    public BaseResponse<Boolean> deleteOrder(@RequestParam Long orderId) {
        Boolean success = ordersnService.deleteOrderById(orderId);
        return ResultUtils.success(success);
    }

    /**
     * 批量删除订单
     *
     * @param orderIds
     * @return
     */
    @PostMapping( "/deleteBathOrder" )
    public BaseResponse<Boolean> deleteOrder(@RequestBody Long[] orderIds) {
        Boolean success = ordersnService.deleteBathOrderByIds(orderIds);
        return ResultUtils.success(success);
    }
}
