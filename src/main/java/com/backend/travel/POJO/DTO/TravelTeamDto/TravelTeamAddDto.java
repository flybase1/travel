package com.backend.travel.POJO.DTO.TravelTeamDto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 队伍添加表
 * @TableName travel_team
 */
@Data
public class TravelTeamAddDto implements Serializable {
    /**
     * 项目id
     */
    private Long travelId;

    /**
     * 最大人员数量/不包括导游
     */
    private Integer maxNum;

    /**
     * 导游个数
     */
    private Integer guideNum;

    /**
     * 目前加入人员的个数
     */
    private Integer nowNum;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建旅游队伍账号的id
     */
    private Long createTeamAccountId;

    /**
     * 队伍名
     */
    private String travelTeamName;

    private static final long serialVersionUID = 1L;
}