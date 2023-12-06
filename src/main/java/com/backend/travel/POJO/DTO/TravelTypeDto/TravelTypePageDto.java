package com.backend.travel.POJO.DTO.TravelTypeDto;

import com.backend.travel.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 旅行项目分页查询
 *
 * @TableName traveltype
 */
@EqualsAndHashCode( callSuper = true )
@Data
public class TravelTypePageDto extends PageRequest implements Serializable {
    /**
     * 旅游类型
     */
    private String queryTravelType;

    private static final long serialVersionUID = 1L;
}