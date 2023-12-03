package com.backend.travel.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.bean.BeanUtil;
import com.backend.travel.POJO.DTO.Travel.TravelPageDto;
import com.backend.travel.POJO.VO.travel.TravelPageVo;
import com.backend.travel.POJO.VO.travel.TravelVo;
import com.backend.travel.POJO.entity.*;
import com.backend.travel.common.CommonConstant;
import com.backend.travel.utils.SqlUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.backend.travel.service.TravelService;
import com.backend.travel.dao.TravelMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author admin
 * @description 针对表【travel(旅游表)】的数据库操作Service实现
 * @createDate 2023-12-03 09:59:36
 */
@Service
public class TravelServiceImpl extends ServiceImpl<TravelMapper, Travel>
        implements TravelService {
    @Resource
    private PictureServiceImpl pictureService;
    @Resource
    private TravelcityServiceImpl travelcityService;
    @Resource
    private TraveltypeServiceImpl traveltypeService;
    @Resource
    private TravelstatusServiceImpl travelstatusService;

    @Override
    public Page<TravelPageVo> getTravelPage(TravelPageDto travelPageDto) {
        Long queryTravelId = travelPageDto.getQueryTravelId();
        Long queryAccountId = travelPageDto.getQueryAccountId();
        String queryTravelTitle = travelPageDto.getQueryTravelTitle();
        Integer queryTypeId = travelPageDto.getQueryTypeId();
        Long queryTravelCityId = travelPageDto.getQueryTravelCityId();
        Double queryTravelScore = travelPageDto.getQueryTravelScore();
        Integer queryTravelStatusId = travelPageDto.getQueryTravelStatusId();
        Date queryBeginTime = travelPageDto.getQueryBeginTime();
        long current = travelPageDto.getCurrent();
        long pageSize = travelPageDto.getPageSize();
        String sortField = travelPageDto.getSortField();
        String sortOrder = travelPageDto.getSortOrder();

        QueryWrapper<Travel> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(queryTravelId != null, "travelId", queryTravelId);
        queryWrapper.eq(queryAccountId != null, "accountId", queryAccountId);
        queryWrapper.like(StringUtils.isNotBlank(queryTravelTitle), "travelTitle", queryTravelTitle);
        queryWrapper.eq(queryTypeId != null, "typeId", queryTypeId);
        queryWrapper.eq(queryTravelCityId != null, "travelCityId", queryTravelCityId);
        queryWrapper.eq(queryTravelScore != null, "travelScore", queryTravelScore);
        queryWrapper.eq(queryTravelStatusId != null, "travelStatusId", queryTravelStatusId);

        queryWrapper.orderBy(SqlUtils.validSortField(sortField),
                sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);

        Page<Travel> travelPage = this.page(new Page<Travel>(current, pageSize), queryWrapper);
        // 遍历转换vo
        List<TravelPageVo> travelPageVoList = travelPage.getRecords().stream().map(travel -> {
            TravelPageVo travelPageVo = new TravelPageVo();
            BeanUtil.copyProperties(travel, travelPageVo);
            Integer typeId = travel.getTypeId();
            Long travelCityId = travel.getTravelCityId();
            Long travelId = travel.getTravelId();
            Integer travelStatusId = travel.getTravelStatusId();
            // 1. 根据typeId查询typeName
            Traveltype traveltype = traveltypeService.getOne(new QueryWrapper<Traveltype>().eq("typeId", typeId));
            String travelTypeName = traveltype.getTravelType();
            travelPageVo.setTypeName(travelTypeName);
            // 2. 根据cityid查找对应cityname
            Travelcity travelcity = travelcityService.getOne(new QueryWrapper<Travelcity>().eq("cityId", travelCityId));
            String cityName = travelcity.getCityName();
            travelPageVo.setTravelCityName(cityName);
            //3. 根据travelId查找图片集合
            List<Picture> pictureList = pictureService.list(new QueryWrapper<Picture>().eq("travelId", travelId));
            travelPageVo.setPictureList(pictureList);
            // 4. 根据travelStatusid获取对应状态
            Travelstatus travelstatus = travelstatusService.getOne(new QueryWrapper<Travelstatus>().eq("travelStatusId", travelStatusId));
            String travelStatusName = travelstatus.getTravelStatusName();
            travelPageVo.setTravelStatusName(travelStatusName);
            // 4. 返回新的vo
            return travelPageVo;
        }).collect(Collectors.toList());

        // 新的vo
        Page<TravelPageVo> pageVoPage = new Page<>();
        pageVoPage.setRecords(travelPageVoList);
        pageVoPage.setTotal(travelPage.getTotal());
        pageVoPage.setCurrent(travelPage.getCurrent());
        pageVoPage.setSize(travelPage.getSize());
        return pageVoPage;
    }

    @Override
    public TravelVo getTravelByTravelId(Long travelId) {
        TravelVo travelVo = new TravelVo();
        Travel travel = this.getOne(new QueryWrapper<Travel>().eq("travelId", travelId));
        BeanUtil.copyProperties(travel, travelVo);
        // 1. 根据typeId查询typeName
        Traveltype traveltype = traveltypeService.getOne(new QueryWrapper<Traveltype>().eq("typeId", travel.getTypeId()));
        String travelTypeName = traveltype.getTravelType();
        travelVo.setTypeName(travelTypeName);
        // 2. 根据cityid查找对应cityname
        Travelcity travelcity = travelcityService.getOne(new QueryWrapper<Travelcity>().eq("cityId", travel.getTravelCityId()));
        String cityName = travelcity.getCityName();
        travelVo.setTravelCityName(cityName);
        //3. 根据travelId查找图片集合
        List<Picture> pictureList = pictureService.list(new QueryWrapper<Picture>().eq("travelId", travelId));
        travelVo.setPictureList(pictureList);

        return travelVo;
    }
}




