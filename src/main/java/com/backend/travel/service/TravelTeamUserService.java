package com.backend.travel.service;

import com.backend.travel.POJO.DTO.TravelTeamUserDto.TravelTeamUserPageDto;
import com.backend.travel.POJO.VO.travelTeamUser.TravelTeamUserVo;
import com.backend.travel.POJO.entity.TravelTeamUser;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author admin
* @description 针对表【travel_team_user(用户队伍表)】的数据库操作Service
* @createDate 2023-12-06 15:54:40
*/
public interface TravelTeamUserService extends IService<TravelTeamUser> {

    /**
     * 分页展示
     * @param travelTeamUserPageDto
     * @return
     */
    Page<TravelTeamUserVo> pageTravelTeamUser(TravelTeamUserPageDto travelTeamUserPageDto);
}
