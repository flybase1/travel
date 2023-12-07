package com.backend.travel.POJO.VO.travelTeam;

import com.backend.travel.POJO.VO.travelTeamUser.TravelTeamUserVo;
import com.backend.travel.POJO.entity.TravelTeamUser;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 队伍表
 *
 * @TableName travel_team
 */
@Data
public class TravelTeamPageVo implements Serializable {
    /**
     * 创建项目队伍id
     */
    private Long travelTeamId;

    /**
     * 项目id
     */
    private Long travelId;

    /**
     *
     */
    private String travelTitle;

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
     * 账号
     */
    private String userAccount;


    private String travelTeamName;

    private Integer travelTeamStatus;

    /**
     * 存储对应的队伍信息
     */
    List<TravelTeamUserVo> travelTeamUserVos;

    private static final long serialVersionUID = 1L;
}