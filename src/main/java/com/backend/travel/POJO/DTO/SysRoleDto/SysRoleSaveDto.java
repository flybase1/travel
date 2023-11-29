package com.backend.travel.POJO.DTO.SysRoleDto;

import com.backend.travel.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
public class SysRoleSaveDto  {
    /**
     * 权限名称
     */
    private String roleName;

    /**
     * 权限标识 admin user guide
     */
    private String roleCode;

    /**
     * 备注
     */
    private String remark;
}
