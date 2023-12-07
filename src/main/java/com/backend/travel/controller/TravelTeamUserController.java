package com.backend.travel.controller;

import com.backend.travel.POJO.DTO.TravelTeamUserDto.TravelTeamUserPageDto;
import com.backend.travel.POJO.VO.travelTeamUser.TravelTeamUserVo;
import com.backend.travel.POJO.entity.TravelTeamUser;
import com.backend.travel.common.BaseResponse;
import com.backend.travel.common.ErrorCode;
import com.backend.travel.common.ResultUtils;
import com.backend.travel.execption.BusinessException;
import com.backend.travel.service.impl.TravelTeamUserServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping( "/travelTeamUser" )
@RestController
public class TravelTeamUserController {
    @Resource
    private TravelTeamUserServiceImpl travelTeamUserService;

    /**
     * 分页展示
     *
     * @param travelTeamUserPageDto
     * @return
     */
    @PostMapping( "/pageTravelTeamUser" )
    public BaseResponse<Page<TravelTeamUserVo>> pageTravelTeamUser(@RequestBody TravelTeamUserPageDto travelTeamUserPageDto) {
        Page<TravelTeamUserVo> travelTeamUserVoPage = travelTeamUserService.pageTravelTeamUser(travelTeamUserPageDto);
        return ResultUtils.success(travelTeamUserVoPage);
    }

    /**
     * 列表展示
     *
     * @param travelTeamId
     * @return
     */
    @GetMapping( "/listTravelTeamUser" )
    public BaseResponse<List<TravelTeamUser>> listTravelTeamUser(Long travelTeamId) {
        List<TravelTeamUser> teamUserList = travelTeamUserService.list(new QueryWrapper<TravelTeamUser>().eq("travelTeamId", travelTeamId));
        return ResultUtils.success(teamUserList);
    }

    /**
     * 批量删除
     *
     * @param travelTeamUserIds
     * @return
     */
    @DeleteMapping( "/deleteTravelTeamUser" )
    public BaseResponse<Boolean> deleteTravelTeamUserByIds(@RequestBody Long[] travelTeamUserIds) {
        List<Long> list = Arrays.stream(travelTeamUserIds).collect(Collectors.toList());
        boolean b = travelTeamUserService.removeBatchByIds(list);
        if (!b) {
            throw new BusinessException(ErrorCode.DATA_DELETE_ERROR);
        }
        return ResultUtils.success(true);
    }
}
