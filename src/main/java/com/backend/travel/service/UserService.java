package com.backend.travel.service;

import com.backend.travel.POJO.DTO.UserDto.UserAvatarUpdate;
import com.backend.travel.POJO.DTO.UserDto.UserPageDto;
import com.backend.travel.POJO.DTO.UserDto.UserUpdateDto;
import com.backend.travel.POJO.VO.user.UserPageVo;
import com.backend.travel.POJO.VO.user.UserVo;
import com.backend.travel.POJO.entity.User;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author admin
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2023-11-26 11:03:08
*/
public interface UserService extends IService<User> {

    /**
     * 批量删除
     * @param userIds
     * @return
     */
    Boolean deleteUserBathOrOne(Long[] userIds);

    /**
     * 更新信息
     * @param userUpdateDto
     * @return
     */
    Boolean updateUser(UserUpdateDto userUpdateDto);

    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    UserVo getUserInfo(Long userId);

    /**
     * 分页
     * @param userPageDto
     * @return
     */
    Page<UserPageVo> pageUserVo(UserPageDto userPageDto);

    /**
     * 更新头像
     * @param userAvatarUpdate
     * @return
     */
    Boolean updateUserAvatar(UserAvatarUpdate userAvatarUpdate);
}
