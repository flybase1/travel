package com.backend.travel.POJO.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 队伍表
 * @TableName travel_team
 */
@TableName(value ="travel_team")
@Data
public class TravelTeam implements Serializable {
    /**
     * 创建项目队伍id
     */
    @TableId(type = IdType.AUTO)
    private Long travelTeamId;

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

    /**
     * 队伍状态 0-开加入 1-不可加入
     */
    private Integer travelTeamStatus;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}