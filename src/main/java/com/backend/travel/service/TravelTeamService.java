package com.backend.travel.service;

import com.backend.travel.POJO.DTO.TravelTeamDto.TravelTeamAddDto;
import com.backend.travel.POJO.DTO.TravelTeamDto.TravelTeamPageDto;
import com.backend.travel.POJO.VO.travelTeam.TravelTeamPageVo;
import com.backend.travel.POJO.entity.TravelTeam;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author admin
 * @description 针对表【travel_team(队伍表)】的数据库操作Service
 * @createDate 2023-12-06 15:54:40
 */
public interface TravelTeamService extends IService<TravelTeam> {
    /**
     * 分页展示
     *
     * @param travelTeamPageDto
     * @return
     */
    Page<TravelTeamPageVo> getTravelTeamPage(TravelTeamPageDto travelTeamPageDto);

    /**
     * 添加队伍
     * @param travelTeamAddDto
     * @return
     */
    Boolean addTravelTeam(TravelTeamAddDto travelTeamAddDto);
}
