package com.backend.travel.service.impl;

import cn.hutool.core.util.StrUtil;
import com.backend.travel.POJO.DTO.CustomedTravelDto.CustomizedTravelPageDto;
import com.backend.travel.common.CommonConstant;
import com.backend.travel.utils.SqlUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.backend.travel.POJO.entity.Customizedtravel;
import com.backend.travel.service.CustomizedtravelService;
import com.backend.travel.dao.CustomizedtravelMapper;
import org.springframework.stereotype.Service;

/**
* @author admin
* @description 针对表【customizedtravel(用户定制旅游)】的数据库操作Service实现
* @createDate 2023-12-15 10:11:34
*/
@Service
public class CustomizedtravelServiceImpl extends ServiceImpl<CustomizedtravelMapper, Customizedtravel>
    implements CustomizedtravelService{

    @Override
    public Page<Customizedtravel> pageCustomizedTravel(CustomizedTravelPageDto customizedTravelPageDto) {
     Long queryCustomizedTravelId = customizedTravelPageDto.getQueryCustomizedTravelId();
     Long queryTravelTypeId = customizedTravelPageDto.getQueryTravelTypeId();
     Long queryTravelCityId = customizedTravelPageDto.getQueryTravelCityId();
     String queryTravelUserName = customizedTravelPageDto.getQueryTravelUserName();
     String queryTravelUserPhoneNum = customizedTravelPageDto.getQueryTravelUserPhoneNum();
     long current = customizedTravelPageDto.getCurrent();
     long pageSize = customizedTravelPageDto.getPageSize();
     String sortField = customizedTravelPageDto.getSortField();
     String sortOrder = customizedTravelPageDto.getSortOrder();

     QueryWrapper<Customizedtravel> queryWrapper = new QueryWrapper<>();
     queryWrapper.eq(queryCustomizedTravelId!=null,"customizedTravelId",queryCustomizedTravelId);
     queryWrapper.eq(queryTravelTypeId!=null,"travelTypeId",queryTravelTypeId);
     queryWrapper.eq(queryTravelCityId!=null,"travelCityId",queryTravelCityId);
     queryWrapper.eq(StrUtil.isNotBlank(queryTravelUserName),"travelUserName",queryTravelUserName);
     queryWrapper.eq(StrUtil.isNotBlank(queryTravelUserPhoneNum),"travelUserPhoneNum",queryTravelUserPhoneNum);

     queryWrapper
                .orderBy(SqlUtils.validSortField(sortField),
                        sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                        sortField);

        Page<Customizedtravel> customizedtravelPage = this.page(new Page<>(current, pageSize), queryWrapper);
        return customizedtravelPage;
    }
}




