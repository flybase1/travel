package com.backend.travel.POJO.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户队伍表
 * @TableName travel_team_user
 */
@TableName(value ="travel_team_user")
@Data
public class TravelTeamUser implements Serializable {
    /**
     * 当前主键
     */
    @TableId(type = IdType.AUTO)
    private Long teamId;

    /**
     * 关联旅游队伍主键
     */
    private Long travelTeamId;

    /**
     * 加入用户的id
     */
    private Long joinAccountId;

    /**
     * 用户加入时间
     */
    private Date joinTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}