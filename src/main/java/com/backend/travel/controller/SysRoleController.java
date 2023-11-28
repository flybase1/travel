package com.backend.travel.controller;

import com.backend.travel.POJO.entity.SysRole;
import com.backend.travel.common.BaseResponse;
import com.backend.travel.common.ResultUtils;
import com.backend.travel.service.impl.SysRoleServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping( "/sysRole" )
@RestController
public class SysRoleController {
    @Resource
    private SysRoleServiceImpl sysRoleService;

    /**列表展示所有权限
     *
     * @return
     */
    @GetMapping( "/listAll" )
    public BaseResponse<List<SysRole>> sysRoleList() {
        List<SysRole> list = sysRoleService.list();
        return ResultUtils.success(list);
    }
}
