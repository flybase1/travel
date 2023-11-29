package com.backend.travel.POJO.DTO.SysRoleDto;

import lombok.Data;


@Data
public class SysRoleUpdateDto {
    private Integer roleId;
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
