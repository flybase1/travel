package com.backend.travel.POJO.VO.comment;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CommentPageVo implements Serializable {

    private static final long serialVersionUID = -3147664222360359112L;
    private Long commentId;

    /**
     * 账号id
     */
    private Long accountId;
    /**
     * 用户账号
     */
    private String userAccount;
    /**
     * 项目id
     */
    private Long travelId;
    /**
     * 项目标题
     */
    private String travelTitle;
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
}
