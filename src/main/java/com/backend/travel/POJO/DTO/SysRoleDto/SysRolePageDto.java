package com.backend.travel.POJO.DTO.SysRoleDto;

import com.backend.travel.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode( callSuper = true )
@Data
public class SysRolePageDto extends PageRequest {
    /**
     * 权限名称
     */
    private String queryRoleName;

    /**
     * 权限标识 admin user guide
     */
    private String queryRoleCode;

    /**
     * 备注
     */
    private String queryRemark;
}
