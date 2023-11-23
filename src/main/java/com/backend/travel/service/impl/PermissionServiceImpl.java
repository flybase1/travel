package com.backend.travel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.backend.travel.POJO.entity.Permission;
import com.backend.travel.service.PermissionService;
import com.backend.travel.dao.PermissionMapper;
import org.springframework.stereotype.Service;

/**
* @author admin
* @description 针对表【permission(权限表)】的数据库操作Service实现
* @createDate 2023-11-23 11:19:26
*/
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission>
    implements PermissionService{

}




