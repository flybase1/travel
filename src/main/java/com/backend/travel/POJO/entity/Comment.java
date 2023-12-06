package com.backend.travel.POJO.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName comment
 */
@TableName(value ="comment")
@Data
public class Comment implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long commentId;

    /**
     * 账号id
     */
    private Long accountId;

    /**
     * 项目id
     */
    private Long travelId;

    /**
     * 评论内容
     */
    private String commentContent;

    /**
     * 评论时间
     */
    private Date createTime;

    /**
     * 项目评分
     */
    private Double projectScore;

    /**
     * 评论状态
     */
    private Integer commentStatus;

    /**
     * 点赞数
     */
    private Long thumbNum;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}