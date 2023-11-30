package com.backend.travel.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.bean.BeanUtil;
import com.backend.travel.POJO.DTO.UserDto.UserPageDto;
import com.backend.travel.POJO.DTO.UserDto.UserUpdateDto;
import com.backend.travel.POJO.VO.user.UserPageVo;
import com.backend.travel.POJO.VO.user.UserVo;
import com.backend.travel.POJO.entity.Account;
import com.backend.travel.POJO.entity.SysRole;
import com.backend.travel.common.CommonConstant;
import com.backend.travel.common.ErrorCode;
import com.backend.travel.execption.BusinessException;
import com.backend.travel.utils.SqlUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.backend.travel.POJO.entity.User;
import com.backend.travel.service.UserService;
import com.backend.travel.dao.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author admin
 * @description 针对表【user(用户表)】的数据库操作Service实现
 * @createDate 2023-11-26 11:03:08
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {
    @Resource
    private AccountServiceImpl accountService;

    @Override
    public Boolean deleteUserBathOrOne(Long[] userIds) {
        return null;
    }

    @Override
    public Boolean updateUser(UserUpdateDto userUpdateDto) {
        User user = new User();
        BeanUtil.copyProperties(userUpdateDto, user);
        boolean b = this.updateById(user);
        if (!b) {
            throw new BusinessException(ErrorCode.DATA_UPDATE_ERROR);
        }
        return true;
    }

    @Override
    public UserVo getUserInfo(Long userId) {
        User user = this.getOne(new QueryWrapper<User>().eq("userId", userId));
        UserVo userVo = new UserVo();
        BeanUtil.copyProperties(user, userVo);
        Account account = accountService.getOne(new QueryWrapper<Account>().eq("accountId", user.getAccountId()));
        userVo.setUserAccount(account.getUserAccount());
        return userVo;
    }

    @Override
    public Page<UserPageVo> pageUserVo(UserPageDto userPageDto) {
        String queryUserName = userPageDto.getQueryUserName();
        Integer queryUserAge = userPageDto.getQueryUserAge();
        String queryUserRegion = userPageDto.getQueryUserRegion();
        Integer queryUserGender = userPageDto.getQueryUserGender();
        Date queryCreateTime = userPageDto.getQueryCreateTime();
        long current = userPageDto.getCurrent();
        long pageSize = userPageDto.getPageSize();
        String sortField = userPageDto.getSortField();
        String sortOrder = userPageDto.getSortOrder();

        // 拼接查询条件
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (queryUserName != null) {
            queryWrapper.or().like("username", queryUserName);
        }
        if (queryUserAge != null) {
            queryWrapper.or().eq("userAge", queryUserAge);
        }
        if (queryCreateTime != null) {
            queryWrapper.or().like("createTime", queryCreateTime);
        }
        if (queryUserGender != null) {
            queryWrapper.or().eq("userGender", queryUserGender);
        }
        if (queryUserRegion != null) {
            queryWrapper.or().eq("userRegion", queryUserRegion);
        }

        // 排序
        queryWrapper
                .orderBy(SqlUtils.validSortField(sortField),
                        sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                        sortField);

        Page<User> page = this.page(new Page<>(current, pageSize), queryWrapper);

        // 转换vo
        List<UserPageVo> userPageVoList = page.getRecords().stream().map(user -> {
            UserPageVo userPageVo = new UserPageVo();
            BeanUtil.copyProperties(user, userPageVo);
            Account account = accountService.getOne(new QueryWrapper<Account>().eq("accountId", user.getAccountId()));
            userPageVo.setUserAccount(account.getUserAccount());
            // 处理UserProfile
            String originalProfile = userPageVo.getUserProfile();
            if (originalProfile != null && originalProfile.length() > 20) {
                String truncatedProfile = originalProfile.substring(0, 20) + "...";
                userPageVo.setUserProfile(truncatedProfile);
            }
            return userPageVo;
        }).collect(Collectors.toList());

        Page<UserPageVo> userPageVoPage = new Page<>();
        userPageVoPage.setPages(page.getPages());
        userPageVoPage.setSize(page.getSize());
        userPageVoPage.setTotal(page.getTotal());
        userPageVoPage.setCurrent(page.getCurrent());
        userPageVoPage.setRecords(userPageVoList);

        return userPageVoPage;
    }
}




