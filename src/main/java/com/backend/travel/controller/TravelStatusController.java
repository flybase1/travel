package com.backend.travel.controller;

import com.backend.travel.POJO.entity.Travelstatus;
import com.backend.travel.common.BaseResponse;
import com.backend.travel.common.ResultUtils;
import com.backend.travel.service.impl.TravelstatusServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping( "/travelstatus" )
@RestController
public class TravelStatusController {

    @Resource
    private TravelstatusServiceImpl travelstatusService;

    @GetMapping( "/listStatus" )
    public BaseResponse<List<Travelstatus>> getListTravleStatus() {
        List<Travelstatus> travelstatusList = travelstatusService.list();
        return ResultUtils.success(travelstatusList);
    }
}
