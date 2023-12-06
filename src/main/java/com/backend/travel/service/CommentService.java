package com.backend.travel.service;

import com.backend.travel.POJO.DTO.CommentDto.CommentPageDto;
import com.backend.travel.POJO.VO.comment.CommentPageVo;
import com.backend.travel.POJO.entity.Comment;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author admin
 * @description 针对表【comment】的数据库操作Service
 * @createDate 2023-12-06 09:30:07
 */
public interface CommentService extends IService<Comment> {

    /**
     * 分页展示
     *
     * @param commentPageDto
     * @return
     */
    Page<CommentPageVo> getAllCommentPage(CommentPageDto commentPageDto);

    /**
     * 评论审核
     *
     * @param commentId
     * @return
     */
    Boolean isCommentValid(Long commentId);

    /**
     * 删除评论列表
     *
     * @param commentIds
     * @return
     */
    Boolean deleteCommentByIds(Long[] commentIds);
}
