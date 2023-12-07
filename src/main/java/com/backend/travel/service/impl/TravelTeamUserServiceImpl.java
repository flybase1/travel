package com.backend.travel.service.impl;

import com.backend.travel.POJO.DTO.TravelTeamUserDto.TravelTeamUserPageDto;
import com.backend.travel.POJO.VO.travelTeamUser.TravelTeamUserVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.backend.travel.POJO.entity.TravelTeamUser;
import com.backend.travel.service.TravelTeamUserService;
import com.backend.travel.dao.TravelTeamUserMapper;
import org.springframework.stereotype.Service;

/**
* @author admin
* @description 针对表【travel_team_user(用户队伍表)】的数据库操作Service实现
* @createDate 2023-12-06 15:54:40
*/
@Service
public class TravelTeamUserServiceImpl extends ServiceImpl<TravelTeamUserMapper, TravelTeamUser>
    implements TravelTeamUserService{

    @Override
    public Page<TravelTeamUserVo> pageTravelTeamUser(TravelTeamUserPageDto travelTeamUserPageDto) {
        return null;
    }
}




