package com.backend.travel.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.backend.travel.POJO.DTO.Order.OrderPageQueryDto;
import com.backend.travel.POJO.DTO.Order.OrderSaveDto;
import com.backend.travel.POJO.DTO.Order.OrderUpdateDto;
import com.backend.travel.POJO.VO.order.OrderPageVo;
import com.backend.travel.POJO.VO.order.OrderVo;
import com.backend.travel.POJO.entity.Orderstatus;
import com.backend.travel.common.CommonConstant;
import com.backend.travel.utils.SqlUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.backend.travel.POJO.entity.Ordersn;
import com.backend.travel.service.OrdersnService;
import com.backend.travel.dao.OrdersnMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author admin
 * @description 针对表【ordersn】的数据库操作Service实现
 * @createDate 2023-12-01 14:59:07
 */
@Service
public class OrdersnServiceImpl extends ServiceImpl<OrdersnMapper, Ordersn>
        implements OrdersnService {
    @Resource
    private OrderstatusServiceImpl orderstatusService;

    @Override
    public Page<OrderPageVo> pageOrder(OrderPageQueryDto orderPageQueryDto) {
        Long accountId = orderPageQueryDto.getQueryAccountId();
        Long travelId = orderPageQueryDto.getQueryTravelId();
        Integer payUtil = orderPageQueryDto.getQueryPayUtil();
        Integer orderStatusId = orderPageQueryDto.getQueryOrderStatusId();
        long current = orderPageQueryDto.getCurrent();
        long pageSize = orderPageQueryDto.getPageSize();
        String sortField = orderPageQueryDto.getSortField();
        String sortOrder = orderPageQueryDto.getSortOrder();

        QueryWrapper<Ordersn> queryWrapper = new QueryWrapper<>();
        if (accountId != null && accountId != -1) {
            queryWrapper.eq("accountId", accountId);
        }
        if (travelId != null && travelId != -1) {
            queryWrapper.eq("travelId", travelId);
        }
        if (payUtil != null && payUtil != -1) {
            queryWrapper.eq("payUtil", payUtil);
        }
        if (orderStatusId != null && orderStatusId != -1) {
            queryWrapper.eq("orderStatusId", orderStatusId);
        }
        queryWrapper
                .orderBy(SqlUtils.validSortField(sortField),
                        sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                        sortField);

        Page<Ordersn> ordersnPage = this.page(new Page<>(current, pageSize), queryWrapper);

        // 1. 关联查询订单状态表
        List<OrderPageVo> orderPageVoList = ordersnPage.getRecords().stream().map(ordersn -> {
            OrderPageVo orderPageVo = new OrderPageVo();
            BeanUtil.copyProperties(ordersn, orderPageVo);
            return orderPageVo;
        }).collect(Collectors.toList());

        Page<OrderPageVo> orderPageVoPage = new Page<>();
        orderPageVoPage.setPages(ordersnPage.getPages());
        orderPageVoPage.setRecords(orderPageVoList);
        orderPageVoPage.setCurrent(ordersnPage.getCurrent());
        orderPageVoPage.setTotal(ordersnPage.getTotal());
        return orderPageVoPage;
    }

    @Override
    public OrderVo getOrderInfo(Long orderId) {
        return null;
    }

    @Override
    public Boolean saveOrder(OrderSaveDto orderSaveDto) {
        return null;
    }

    @Override
    public Boolean updateOrder(OrderUpdateDto orderUpdateDto) {
        return null;
    }

    @Override
    public Boolean deleteOrderById(Long orderId) {
        return null;
    }

    @Override
    public Boolean deleteBathOrderByIds(Long[] orderIds) {
        return null;
    }
}




