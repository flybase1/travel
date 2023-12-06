package com.backend.travel.POJO.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 导游队伍表
 * @TableName travel_team_guide
 */
@TableName(value ="travel_team_guide")
@Data
public class TravelTeamGuide implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long travelTeamGuideId;

    /**
     * 导游id
     */
    private Long guideId;

    /**
     * 关联旅游队伍表
     */
    private Long travelTeamId;

    /**
     * 接单加入时间
     */
    private Date joinTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}