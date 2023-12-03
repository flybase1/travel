package com.backend.travel.controller;

import com.backend.travel.POJO.entity.Traveltype;
import com.backend.travel.common.BaseResponse;
import com.backend.travel.common.ResultUtils;
import com.backend.travel.service.impl.TraveltypeServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping( "/travelType" )
@RestController
public class TravelTypeController {
    @Resource
    private TraveltypeServiceImpl travelTypeService;

    @GetMapping( "/listType" )
    public BaseResponse<List<Traveltype>> listTravelType() {
        List<Traveltype> list = travelTypeService.list();
        return ResultUtils.success(list);
    }
}
