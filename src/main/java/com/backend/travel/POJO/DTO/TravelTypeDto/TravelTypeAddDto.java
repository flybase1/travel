package com.backend.travel.POJO.DTO.TravelTypeDto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 旅行项目新建
 * @TableName traveltype
 */
@Data
public class TravelTypeAddDto implements Serializable {
    /**
     *旅游类型
     */
    private String travelType;

    private static final long serialVersionUID = 1L;
}