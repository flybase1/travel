package com.backend.travel.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.backend.travel.POJO.DTO.TravelTeamDto.TravelTeamAddDto;
import com.backend.travel.POJO.DTO.TravelTeamDto.TravelTeamChangeStatusDto;
import com.backend.travel.POJO.DTO.TravelTeamDto.TravelTeamPageDto;
import com.backend.travel.POJO.DTO.TravelTeamDto.TravelTeamUpdateDto;
import com.backend.travel.POJO.VO.travelTeam.TravelTeamPageVo;
import com.backend.travel.POJO.VO.travelTeamUser.TravelTeamUserVo;
import com.backend.travel.POJO.entity.Account;
import com.backend.travel.POJO.entity.Travel;
import com.backend.travel.POJO.entity.TravelTeamUser;
import com.backend.travel.common.CommonConstant;
import com.backend.travel.common.ErrorCode;
import com.backend.travel.execption.BusinessException;
import com.backend.travel.utils.SqlUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.backend.travel.POJO.entity.TravelTeam;
import com.backend.travel.service.TravelTeamService;
import com.backend.travel.dao.TravelTeamMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author admin
 * @description 针对表【travel_team(队伍表)】的数据库操作Service实现
 * @createDate 2023-12-06 15:54:40
 */
@Service
public class TravelTeamServiceImpl extends ServiceImpl<TravelTeamMapper, TravelTeam>
        implements TravelTeamService {
    @Resource
    private AccountServiceImpl accountService;
    @Resource
    private TravelServiceImpl travelService;
    @Resource
    private TravelTeamUserServiceImpl travelTeamUserService;

    @Override
    public Page<TravelTeamPageVo> getTravelTeamPage(TravelTeamPageDto travelTeamPageDto) {
        Long queryTravelTeamId = travelTeamPageDto.getQueryTravelTeamId();
        Long queryTravelId = travelTeamPageDto.getQueryTravelId();
        String queryTravelTitle = travelTeamPageDto.getQueryTravelTitle();
        Long queryCreateTeamAccountId = travelTeamPageDto.getQueryCreateTeamAccountId();
        String queryUserAccount = travelTeamPageDto.getQueryUserAccount();
        long current = travelTeamPageDto.getCurrent();
        long pageSize = travelTeamPageDto.getPageSize();
        String sortField = travelTeamPageDto.getSortField();
        String sortOrder = travelTeamPageDto.getSortOrder();

        QueryWrapper<TravelTeam> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(queryTravelId != null, "travelId", queryTravelId);
        queryWrapper.eq(queryCreateTeamAccountId != null, "accountId", queryCreateTeamAccountId);
        queryWrapper.eq(queryTravelTeamId != null, "travelTeamId", queryTravelTeamId);

        queryWrapper
                .orderBy(SqlUtils.validSortField(sortField),
                        sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                        sortField);

        Page<TravelTeam> page = this.page(new Page<>(current, pageSize), queryWrapper);

        List<TravelTeamPageVo> teamPageVoList = page.getRecords().stream().map(travelTeam -> {
            TravelTeamPageVo travelTeamPageVo = new TravelTeamPageVo();
            BeanUtil.copyProperties(travelTeam, travelTeamPageVo);
            Long travelId = travelTeam.getTravelId();
            Travel travel = travelService.getOne(new QueryWrapper<Travel>().eq("travelId", travelId));
            Long accountId = travelTeam.getCreateTeamAccountId();
            Account account = accountService.getOne(new QueryWrapper<Account>().eq("accountId", accountId));
            travelTeamPageVo.setTravelTitle(travel.getTravelTitle());
            travelTeamPageVo.setUserAccount(account.getUserAccount());
            // 关联用户-队伍 todo 关联更多信息
            List<TravelTeamUser> travelTeamUserList = travelTeamUserService.list(new QueryWrapper<TravelTeamUser>().eq("travelTeamId", travelTeam.getTravelTeamId()));
            // 重新扩充字段
            List<TravelTeamUserVo> travelTeamUserVoList = travelTeamUserList.stream().map(travelTeamUser -> {
                TravelTeamUserVo travelTeamUserVo = new TravelTeamUserVo();
                BeanUtil.copyProperties(travelTeamUser, travelTeamUserVo);
                // 1.  加入账号id
                Long joinAccountId = travelTeamUserVo.getJoinAccountId();
                Account joinAccount = accountService.getOne(new QueryWrapper<Account>().eq("accountId", joinAccountId));
                travelTeamUserVo.setUserAccount(joinAccount.getUserAccount());
                // 2. 加入队伍名字
                Long travelTeamId = travelTeamUserVo.getTravelTeamId();
                TravelTeam joinTravelTeam = this.getOne(new QueryWrapper<TravelTeam>().eq("travelTeamId", travelTeamId));
                travelTeamUserVo.setTravelTeamName(joinTravelTeam.getTravelTeamName());
                // 返回
                return travelTeamUserVo;
            }).collect(Collectors.toList());

            travelTeamPageVo.setTravelTeamUserVos(travelTeamUserVoList);
            return travelTeamPageVo;
        }).collect(Collectors.toList());

        Page<TravelTeamPageVo> teamPageVoPage = new Page<TravelTeamPageVo>(page.getCurrent(), page.getSize());
        teamPageVoPage.setRecords(teamPageVoList);
        teamPageVoPage.setTotal(page.getTotal());
        teamPageVoPage.setPages(page.getPages());
        return teamPageVoPage;
    }

    @Override
    public Boolean addTravelTeam(TravelTeamAddDto travelTeamAddDto) {
        TravelTeam travelTeam = new TravelTeam();
        BeanUtil.copyProperties(travelTeamAddDto, travelTeam);
        travelTeam.setCreateTime(new Date());
        boolean save = this.save(travelTeam);
        if (!save) {
            throw new BusinessException(ErrorCode.DATA_INSERT_ERROR);
        }
        return true;
    }

    @Override
    public Boolean updateTravelTeam(TravelTeamUpdateDto travelTeamUpdateDto) {
        TravelTeam travelTeam = new TravelTeam();
        BeanUtil.copyProperties(travelTeamUpdateDto, travelTeam);
        boolean update = this.updateById(travelTeam);
        if (!update) {
            throw new BusinessException(ErrorCode.DATA_UPDATE_ERROR);
        }
        return true;
    }

    @Override
    public Boolean deleteTravelTeamByIds(Long[] travelTeamIds) {
        List<Long> list = Arrays.stream(travelTeamIds).collect(Collectors.toList());

        // todo 联合删除账号-队伍，队伍-导游表
        boolean b = this.removeBatchByIds(list);
        if (!b) {
            throw new BusinessException(ErrorCode.DATA_DELETE_ERROR);
        }
        return true;
    }

    @Override
    public Boolean changeTravelTeamStatus(Long travelTeamId) {
        TravelTeam travelTeam = this.getOne(new QueryWrapper<TravelTeam>().eq("travelTeamId", travelTeamId));

        if (travelTeam.getTravelTeamStatus() == 0) {
            travelTeam.setTravelTeamStatus(1);
        } else {
            travelTeam.setTravelTeamStatus(0);
        }
        boolean b = this.updateById(travelTeam);
        if (!b) {
            throw new BusinessException(ErrorCode.DATA_UPDATE_ERROR);
        }
        return true;
    }
}




