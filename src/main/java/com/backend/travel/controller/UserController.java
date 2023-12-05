package com.backend.travel.controller;

import com.backend.travel.POJO.DTO.UserDto.UserAvatarUpdate;
import com.backend.travel.POJO.DTO.UserDto.UserPageDto;
import com.backend.travel.POJO.DTO.UserDto.UserSaveDto;
import com.backend.travel.POJO.DTO.UserDto.UserUpdateDto;

import com.backend.travel.POJO.VO.user.UserPageVo;
import com.backend.travel.POJO.VO.user.UserVo;
import com.backend.travel.common.BaseResponse;
import com.backend.travel.common.ResultUtils;
import com.backend.travel.service.impl.UserServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RequestMapping( "/user" )
@RestController
public class UserController {
    @Resource
    private UserServiceImpl userService;

    /**
     * 分页查询用户信息
     *
     * @param userPageDto
     * @return
     */
    @PostMapping( "/pageUser" )
    public BaseResponse<Page<UserPageVo>> pageUserVo(@RequestBody UserPageDto userPageDto) {
        Page<UserPageVo> userPageVoPage = userService.pageUserVo(userPageDto);
        return ResultUtils.success(userPageVoPage);
    }

    /**
     * 获取指定id用户的信息
     *
     * @param userId
     * @return
     */
    @GetMapping( "/getUserInfo" )
    public BaseResponse<UserVo> getUserInfo(@RequestParam Long userId) {
        UserVo userVo = userService.getUserInfo(userId);
        return ResultUtils.success(userVo);
    }

    /**
     * 新建用户
     * @param
     * @return
     */
    // todo 因为创建账号的控制权在account里面，这里不需要新建
//    @PostMapping( "/saveUser" )
//    public BaseResponse<Boolean> saveUser(@RequestBody UserSaveDto userSaveDto) {
//        Boolean success = userService.saveUser(userSaveDto);
//        return ResultUtils.success(success);
//    }

    /**
     * 根据id修改用户信息
     *
     * @param userUpdateDto
     * @return
     */
    @PutMapping( "/updateUser" )
    public BaseResponse<Boolean> updateUser(@RequestBody UserUpdateDto userUpdateDto) {
        Boolean success = userService.updateUser(userUpdateDto);
        return ResultUtils.success(success);
    }

    /**
     * 批量删除或者单个删除用户信息
     *
     * @param userIds
     * @return
     */
    @DeleteMapping( "/deleteUser" )
    public BaseResponse<Boolean> deleteUserBathOrOne(@RequestBody Long[] userIds) {
        Boolean success = userService.deleteUserBathOrOne(userIds);
        return ResultUtils.success(success);
    }

    /**
     * 更新头像
     * @param userAvatarUpdate
     * @return
     */
    @PostMapping( "/uploadAvatar" )
    public BaseResponse<Boolean> uploadUserAvatar(@RequestBody UserAvatarUpdate userAvatarUpdate) {
        Boolean success = userService.updateUserAvatar(userAvatarUpdate);
        return ResultUtils.success(success);
    }
}
