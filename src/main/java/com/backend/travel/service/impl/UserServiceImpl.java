package com.backend.travel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.backend.travel.POJO.entity.User;
import com.backend.travel.service.UserService;
import com.backend.travel.dao.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author admin
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2023-11-23 11:19:17
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




