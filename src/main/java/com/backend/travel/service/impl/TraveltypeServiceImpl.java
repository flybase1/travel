package com.backend.travel.service.impl;

import com.backend.travel.POJO.DTO.TravelTypeDto.TravelTypePageDto;
import com.backend.travel.common.CommonConstant;
import com.backend.travel.common.ResultUtils;
import com.backend.travel.utils.SqlUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.backend.travel.POJO.entity.Traveltype;
import com.backend.travel.service.TraveltypeService;
import com.backend.travel.dao.TraveltypeMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author admin
 * @description 针对表【traveltype(旅行项目)】的数据库操作Service实现
 * @createDate 2023-12-03 10:25:33
 */
@Service
public class TraveltypeServiceImpl extends ServiceImpl<TraveltypeMapper, Traveltype>
        implements TraveltypeService {

    @Override
    public Page<Traveltype> pageTravelType(TravelTypePageDto travelTypePageDto) {
        String queryTravelType = travelTypePageDto.getQueryTravelType();
        long current = travelTypePageDto.getCurrent();
        long pageSize = travelTypePageDto.getPageSize();
        String sortField = travelTypePageDto.getSortField();
        String sortOrder = travelTypePageDto.getSortOrder();

        QueryWrapper<Traveltype> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(queryTravelType), "travelType", queryTravelType);

        queryWrapper.orderBy(SqlUtils.validSortField(sortField),
                sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);

        return this.page(new Page<>(current, pageSize), queryWrapper);
    }
}




