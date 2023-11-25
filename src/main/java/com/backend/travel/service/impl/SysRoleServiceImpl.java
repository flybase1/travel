package com.backend.travel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.backend.travel.POJO.entity.SysRole;
import com.backend.travel.service.SysRoleService;
import com.backend.travel.dao.SysRoleMapper;
import org.springframework.stereotype.Service;

/**
* @author admin
* @description 针对表【sys_role(权限表)】的数据库操作Service实现
* @createDate 2023-11-24 14:26:33
*/
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole>
    implements SysRoleService{

}




