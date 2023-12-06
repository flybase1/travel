package com.backend.travel.controller;

import com.backend.travel.POJO.DTO.CommentDto.CommentPageDto;
import com.backend.travel.POJO.VO.comment.CommentPageVo;
import com.backend.travel.common.BaseResponse;
import com.backend.travel.common.ResultUtils;
import com.backend.travel.service.impl.CommentServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RequestMapping( "/comment" )
@RestController
public class CommentController {
    @Resource
    private CommentServiceImpl commentService;

    /**
     * 分页展示评论
     *
     * @param commentPageDto
     * @return
     */
    @PostMapping( "/pageComment" )
    public BaseResponse<Page<CommentPageVo>> getPageComment(@RequestBody CommentPageDto commentPageDto) {
        Page<CommentPageVo> commentPageVoPage = commentService.getAllCommentPage(commentPageDto);
        return ResultUtils.success(commentPageVoPage);
    }

    /**
     * 管理员审核评论是否违规
     *
     * @param commentId
     * @return
     */
    @GetMapping( "/isValid" )
    public BaseResponse<Boolean> isCommentValid(@RequestParam Long commentId) {
        Boolean success = commentService.isCommentValid(commentId);
        return ResultUtils.success(success);
    }

    /**
     * 删除评论列表
     *
     * @param commentIds
     * @return
     */
    @PostMapping( "/deleteComment" )
    public BaseResponse<Boolean> deleteComment(@RequestBody Long[] commentIds) {
        Boolean success = commentService.deleteCommentByIds(commentIds);
        return ResultUtils.success(success);
    }
}
