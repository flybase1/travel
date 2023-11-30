package com.backend.travel.POJO.DTO.UserDto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserSaveDto implements Serializable {
    /**
     * 用户昵称
     */
    private String username;

    private static final long serialVersionUID = 1L;

}
