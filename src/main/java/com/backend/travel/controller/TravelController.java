package com.backend.travel.controller;

import com.backend.travel.POJO.DTO.Travel.TravelAddDto;
import com.backend.travel.POJO.DTO.Travel.TravelPageDto;
import com.backend.travel.POJO.DTO.Travel.TravelUpdateDto;
import com.backend.travel.POJO.VO.travel.TravelPageVo;
import com.backend.travel.POJO.VO.travel.TravelVo;
import com.backend.travel.common.BaseResponse;
import com.backend.travel.common.ResultUtils;
import com.backend.travel.service.impl.TravelServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 旅游项目controller
 */
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

    /**
     * 获取指定旅游项目
     *
     * @param travelId
     * @return
     */
    @GetMapping( "/getTravel" )
    public BaseResponse<TravelVo> getTravelByTraveId(@RequestParam( "travelId" ) Long travelId) {
        TravelVo travelVo = travelService.getTravelByTravelId(travelId);
        return ResultUtils.success(travelVo);
    }

    /**
     * 添加旅游项目
     *
     * @param travelAddDto
     * @return
     */
    @PostMapping( "/addTravel" )
    public BaseResponse<Boolean> addTravel(@RequestBody TravelAddDto travelAddDto) {
        Boolean success = travelService.addTrave(travelAddDto);
        return ResultUtils.success(success);
    }

    /**
     * 更新旅游项目
     *
     * @param travelUpdateDto
     * @return
     */
    @PutMapping( "/updateTravel" )
    public BaseResponse<Boolean> updateTravel(@RequestBody TravelUpdateDto travelUpdateDto) {
        Boolean success = travelService.updateTravel(travelUpdateDto);
        return ResultUtils.success(success);
    }

    /**
     * 删除指定旅游项目
     *
     * @param travelId
     * @return
     */
    @DeleteMapping( "/deleteTravel" )
    public BaseResponse<Boolean> deleteTravelById(@RequestParam( "travelId" ) Long travelId) {
        Boolean success = travelService.deleteTravelById(travelId);
        return ResultUtils.success(success);
    }

    /**
     * 删除旅游项目列表
     *
     * @param travelIds
     * @return
     */
    @PostMapping( "/deleteTravels" )
    public BaseResponse<Boolean> deleteTravels(@RequestBody Long[] travelIds) {
        Boolean success = travelService.deleteTravels(travelIds);
        return ResultUtils.success(success);
    }

    /**
     * 上线旅游项目
     *
     * @param travelId
     * @return
     */
    @PostMapping( "/onlineProject" )
    public BaseResponse<Boolean> onlineProject(@RequestParam Long travelId) {
        Boolean success = travelService.onlineProject(travelId);
        return ResultUtils.success(success);
    }

    /**
     * 下线旅游项目
     *
     * @param travelId
     * @return
     */
    @PostMapping( "/offLineProject" )
    public BaseResponse<Boolean> offLineProject(@RequestParam Long travelId) {
        Boolean success = travelService.offlineProject(travelId);
        return ResultUtils.success(success);
    }



}
