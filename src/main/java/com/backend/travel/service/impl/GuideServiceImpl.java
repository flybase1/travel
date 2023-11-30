package com.backend.travel.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.bean.BeanUtil;
import com.backend.travel.POJO.DTO.GuideDto.GuidePageDto;
import com.backend.travel.POJO.VO.guide.GuideInfoVo;
import com.backend.travel.POJO.VO.guide.GuidePageVo;
import com.backend.travel.POJO.VO.guide.GuideUpdateVo;
import com.backend.travel.POJO.entity.Account;
import com.backend.travel.POJO.entity.SysAccountRole;
import com.backend.travel.POJO.entity.User;
import com.backend.travel.common.CommonConstant;
import com.backend.travel.common.ErrorCode;
import com.backend.travel.execption.BusinessException;
import com.backend.travel.utils.SqlUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.backend.travel.POJO.entity.Guide;
import com.backend.travel.service.GuideService;
import com.backend.travel.dao.GuideMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author admin
 * @description 针对表【guide(导游表)】的数据库操作Service实现
 * @createDate 2023-11-30 15:47:17
 */
@Service
public class GuideServiceImpl extends ServiceImpl<GuideMapper, Guide>
        implements GuideService {
    @Resource
    private UserServiceImpl userService;
    @Resource
    private AccountServiceImpl accountService;
    @Resource
    private SysAccountRoleServiceImpl sysAccountRoleService;

    @Override
    public Page<GuidePageVo> showGuidePage(GuidePageDto guidePageDto) {
        Integer approvalStatus = guidePageDto.getQueryApprovalStatus();
        Double score = guidePageDto.getQueryScore();
        long current = guidePageDto.getCurrent();
        long pageSize = guidePageDto.getPageSize();
        String sortField = guidePageDto.getSortField();
        String sortOrder = guidePageDto.getSortOrder();

        QueryWrapper<Guide> queryWrapper = new QueryWrapper<>();
        if (approvalStatus != null) {
            queryWrapper.eq("approvalStatus", approvalStatus);
        }
        if (score != null) {
            queryWrapper.eq("score", score);
        }
        // 排序
        queryWrapper
                .orderBy(SqlUtils.validSortField(sortField),
                        sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                        sortField);

        Page<Guide> guidePage = this.page(new Page<>(current, pageSize), queryWrapper);

        // 获取详细信息
        List<GuidePageVo> guidePageVoList = guidePage.getRecords().stream().map(guide -> {
            GuidePageVo guidePageVo = new GuidePageVo();
            User user = userService.getOne(new QueryWrapper<User>().eq("userId", guide.getUserId()));
            Long accountId = user.getAccountId();
            Account account = accountService.getOne(new QueryWrapper<Account>().eq("accountId", accountId));
            BeanUtil.copyProperties(guide, guidePageVo);
            guidePageVo.setAccountId(accountId);
            guidePageVo.setUsername(user.getUsername());
            guidePageVo.setUserAvatar(user.getUserAvatar());
            guidePageVo.setUserAccount(account.getUserAccount());
            return guidePageVo;
        }).collect(Collectors.toList());

        Page<GuidePageVo> guidePageVoPage = new Page<>(current, pageSize);
        guidePageVoPage.setRecords(guidePageVoList);
        guidePageVoPage.setTotal(guidePage.getTotal());
        guidePageVoPage.setPages(guidePage.getPages());
        guidePageVoPage.setCurrent(guidePage.getCurrent());
        return guidePageVoPage;
    }

    @Override
    public GuideInfoVo showGuideInfoById(Long guideId) {
        GuideInfoVo guideInfoVo = new GuideInfoVo();
        Guide guide = this.getOne(new QueryWrapper<Guide>().eq("guideId", guideId));
        // 赋值当前guide所有信息
        BeanUtil.copyProperties(guide, guideInfoVo);
        // 查找user信息
        Long userId = guide.getUserId();
        User user = userService.getOne(new QueryWrapper<User>().eq("userId", userId));
        guideInfoVo.setUsername(user.getUsername());
        guideInfoVo.setUserAvatar(user.getUserAvatar());
        // 查找account信息
        Long accountId = user.getAccountId();
        Account account = accountService.getOne(new QueryWrapper<Account>().eq("accountId", accountId));
        guideInfoVo.setUserAccount(account.getUserAccount());
        return guideInfoVo;
    }

    @Override
    @Transactional
    public Boolean deleteGuideInfoById(Long guideId) {
        Guide guide = this.getOne(new QueryWrapper<Guide>().eq("guideId", guideId));
        // 1. 找到用户id
        Long userId = guide.getUserId();
        User user = userService.getOne(new QueryWrapper<User>().eq("userId", userId));
        // 2. 找到账号id
        Long accountId = user.getAccountId();
        // 3. 关联账号-角色id，进行删除
        boolean removeAccountRole = sysAccountRoleService.remove(new QueryWrapper<SysAccountRole>().eq("accountId", accountId).eq("roleId", 3));
        if (!removeAccountRole) {
            throw new BusinessException(ErrorCode.DATA_DELETE_ERROR);
        }
        // 4. 删除记录
        boolean b = this.removeById(guideId);
        if (!b) {
            throw new BusinessException(ErrorCode.DATA_DELETE_ERROR);
        }
        return true;
    }

    @Override
    public Boolean updateGuideInfo(GuideUpdateVo guideUpdateVo) {
        Guide guide = new Guide();
        BeanUtil.copyProperties(guideUpdateVo, guide);
        boolean b = this.updateById(guide);
        if (!b) {
            throw new BusinessException(ErrorCode.DATA_UPDATE_ERROR);
        }
        return true;
    }
}




