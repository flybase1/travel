package com.backend.travel.controller;

import cn.hutool.core.bean.BeanUtil;
import com.backend.travel.POJO.DTO.TravelTeamDto.TravelTeamAddDto;
import com.backend.travel.POJO.DTO.TravelTeamDto.TravelTeamChangeStatusDto;
import com.backend.travel.POJO.DTO.TravelTeamDto.TravelTeamPageDto;
import com.backend.travel.POJO.DTO.TravelTeamDto.TravelTeamUpdateDto;
import com.backend.travel.POJO.VO.travelTeam.TravelTeamPageVo;
import com.backend.travel.POJO.VO.travelTeam.TravelTeamVo;
import com.backend.travel.POJO.entity.TravelTeam;
import com.backend.travel.common.BaseResponse;
import com.backend.travel.common.ResultUtils;
import com.backend.travel.service.impl.TravelTeamServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping( "/travelTeam" )
public class TravelTeamController {
    @Resource
    private TravelTeamServiceImpl travelTeamService;

    /**
     * 分页展示队伍
     *
     * @param travelTeamPageDto
     * @return
     */
    @PostMapping( "/pageTravelTeam" )
    public BaseResponse<Page<TravelTeamPageVo>> getTravelTeamPage(@RequestBody TravelTeamPageDto travelTeamPageDto) {
        Page<TravelTeamPageVo> pageVoPage = travelTeamService.getTravelTeamPage(travelTeamPageDto);
        return ResultUtils.success(pageVoPage);
    }

    /**
     * 获取指定队伍
     *
     * @param travelTeamId
     * @return
     */
    @GetMapping( "/getTravelTeamById/{travelTeamId}" )
    public BaseResponse<TravelTeamVo> getTravelTeam(@PathVariable Long travelTeamId) {
        TravelTeam travelTeam = travelTeamService.getOne(new QueryWrapper<TravelTeam>().eq("travelTeamId", travelTeamId));
        TravelTeamVo travelTeamVo = new TravelTeamVo();
        BeanUtil.copyProperties(travelTeam, travelTeamVo);
        return ResultUtils.success(travelTeamVo);
    }

    /**
     * 修改指定队伍
     *
     * @param travelTeamAddDto
     * @return
     */
    @PostMapping( "/addTravelTeam" )
    public BaseResponse<Boolean> addTravelTeam(@RequestBody TravelTeamAddDto travelTeamAddDto) {
        Boolean addTravelTeam = travelTeamService.addTravelTeam(travelTeamAddDto);
        return ResultUtils.success(addTravelTeam);
    }

    /**
     * 更新队伍
     *
     * @param travelTeamUpdateDto
     * @return
     */
    @PutMapping( "/updateTravelTeam" )
    public BaseResponse<Boolean> updateTravelTeam(@RequestBody TravelTeamUpdateDto travelTeamUpdateDto) {
        Boolean success = travelTeamService.updateTravelTeam(travelTeamUpdateDto);
        return ResultUtils.success(success);
    }

    /**
     * 删除结合队伍
     *
     * @param travelTeamIds
     * @return
     */
    @DeleteMapping( "/deleteTravelTeams" )
    public BaseResponse<Boolean> deleteTravelTeams(@RequestBody Long[] travelTeamIds) {
        Boolean success = travelTeamService.deleteTravelTeamByIds(travelTeamIds);
        return ResultUtils.success(success);
    }

    /**
     * 修改状态
     *
     * @return
     */
    @PutMapping( "/changeStatus/{travelTeamId}" )
    public BaseResponse<Boolean> changeStatus(@PathVariable Long travelTeamId) {
        Boolean success = travelTeamService.changeTravelTeamStatus(travelTeamId);
        return ResultUtils.success(success);
    }


}
