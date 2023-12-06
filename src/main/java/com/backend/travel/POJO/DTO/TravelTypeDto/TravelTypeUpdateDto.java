package com.backend.travel.POJO.DTO.TravelTypeDto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 旅行项目类型修改
 *
 * @TableName traveltype
 */
@Data
public class TravelTypeUpdateDto implements Serializable {

    /**
     * id
     */
    private Integer typeId;
    /**
     * 旅游类型
     */
    private String travelType;

    private static final long serialVersionUID = 1L;
}