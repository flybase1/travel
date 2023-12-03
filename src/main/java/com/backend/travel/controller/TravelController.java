package com.backend.travel.controller;

import com.backend.travel.POJO.DTO.Travel.TravelPageDto;
import com.backend.travel.POJO.VO.travel.TravelPageVo;
import com.backend.travel.POJO.VO.travel.TravelVo;
import com.backend.travel.common.BaseResponse;
import com.backend.travel.common.ResultUtils;
import com.backend.travel.service.impl.TravelServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RequestMapping( "/project" )
@RestController
public class TravelController {
    @Resource
    private TravelServiceImpl travelService;

    /**
     * 分页展示旅行项目
     *
     * @param travelPageDto
     * @return
     */
    @PostMapping( "/pageProject" )
    public BaseResponse<Page<TravelPageVo>> getTravelPageVo(@RequestBody TravelPageDto travelPageDto) {
        Page<TravelPageVo> travelPageVoPage = travelService.getTravelPage(travelPageDto);
        return ResultUtils.success(travelPageVoPage);
    }

    @GetMapping( "/getTravel" )
    public BaseResponse<TravelVo> getTravelByTraveId(@RequestParam( "travelId" ) Long travelId) {
        TravelVo travelVo = travelService.getTravelByTravelId(travelId);
        return ResultUtils.success(travelVo);
    }
}
