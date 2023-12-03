package com.backend.travel.controller;

import com.backend.travel.POJO.entity.Travelcity;
import com.backend.travel.common.BaseResponse;
import com.backend.travel.common.ResultUtils;
import com.backend.travel.service.impl.TravelcityServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping( "/travelCity" )
@RestController
public class TravelCityController {
    @Resource
    private TravelcityServiceImpl travelcityService;

    @GetMapping( "/listCitys" )
    public BaseResponse<List<Travelcity>> getTravelCityList() {
        List<Travelcity> travelcityList = travelcityService.list();
        return ResultUtils.success(travelcityList);
    }

}
