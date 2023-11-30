package com.backend.travel.POJO.DTO.UserDto;

import com.backend.travel.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode( callSuper = true )
@Data
public class UserPageDto extends PageRequest implements Serializable {
    /**
     * 用户昵称
     */
    private String queryUserName;

    /**
     * 用户年龄
     */
    private Integer queryUserAge;

    /**
     * 用户地区
     */
    private String queryUserRegion;

    /**
     * 用户性别 0-男 1-女
     */
    private Integer queryUserGender;

    /**
     * 创建时间
     */
    private Date queryCreateTime;

}
