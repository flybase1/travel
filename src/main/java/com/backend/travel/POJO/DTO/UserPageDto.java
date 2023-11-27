package com.backend.travel.POJO.DTO;

import com.backend.travel.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode( callSuper = true )
@Data
public class UserPageDto extends PageRequest {
    // 字符串查询条件
    private String queryStr;
    // Integer查询条件
    private Integer queryInt;
    // date查询
    private Date queryDate;
}
