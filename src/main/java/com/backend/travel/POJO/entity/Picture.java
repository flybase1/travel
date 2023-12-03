package com.backend.travel.POJO.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 图床地址
 * @TableName picture
 */
@TableName(value ="picture")
@Data
public class Picture implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long pictureId;

    /**
     * 图片地址
     */
    private String pictureUrl;

    /**
     * 项目id
     */
    private Long travelId;

    /**
     * 评论id
     */
    private Long commentId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}