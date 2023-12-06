package com.backend.travel.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.backend.travel.POJO.DTO.CommentDto.CommentPageDto;
import com.backend.travel.POJO.VO.comment.CommentPageVo;
import com.backend.travel.POJO.entity.Account;
import com.backend.travel.POJO.entity.Travel;
import com.backend.travel.common.CommonConstant;
import com.backend.travel.common.ErrorCode;
import com.backend.travel.execption.BusinessException;
import com.backend.travel.utils.SqlUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.backend.travel.POJO.entity.Comment;
import com.backend.travel.service.CommentService;
import com.backend.travel.dao.CommentMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author admin
 * @description 针对表【comment】的数据库操作Service实现
 * @createDate 2023-12-06 09:30:07
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
        implements CommentService {
    @Resource
    private AccountServiceImpl accountService;
    @Resource
    private TravelServiceImpl travelService;

    @Override
    public Page<CommentPageVo> getAllCommentPage(CommentPageDto commentPageDto) {
        Long queryTravelId = commentPageDto.getQueryTravelId();
        Long queryAccountId = commentPageDto.getQueryAccountId();
        long current = commentPageDto.getCurrent();
        long pageSize = commentPageDto.getPageSize();
        String sortField = commentPageDto.getSortField();
        String sortOrder = commentPageDto.getSortOrder();

        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(queryTravelId != null, "travelId", queryTravelId);
        queryWrapper.eq(queryAccountId != null, "accountId", queryAccountId);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField),
                sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);

        Page<Comment> commentPage = this.page(new Page<>(current, pageSize), queryWrapper);

        List<CommentPageVo> pageVoList = commentPage.getRecords().stream().map(comment -> {
            CommentPageVo commentPageVo = new CommentPageVo();
            BeanUtil.copyProperties(comment, commentPageVo);
            Long accountId = comment.getAccountId();
            Long travelId = comment.getTravelId();
            Account account = accountService.getOne(new QueryWrapper<Account>().eq("accountId", accountId));
            Travel travel = travelService.getOne(new QueryWrapper<Travel>().eq("travelId", travelId));
            commentPageVo.setUserAccount(account.getUserAccount());
            commentPageVo.setTravelTitle(travel.getTravelTitle());
            return commentPageVo;
        }).collect(Collectors.toList());


        Page<CommentPageVo> pageVoPage = new Page<>();
        pageVoPage.setRecords(pageVoList);
        pageVoPage.setCurrent(commentPage.getCurrent());
        pageVoPage.setSize(commentPage.getSize());
        pageVoPage.setTotal(commentPage.getTotal());
        return pageVoPage;
    }

    @Override
    public Boolean isCommentValid(Long commentId) {
        Comment comment = this.getOne(new QueryWrapper<Comment>().eq("commentId", commentId));
        // 违规
        comment.setCommentStatus(1);
        boolean b = this.updateById(comment);
        if (!b) {
            throw new BusinessException(ErrorCode.DATA_UPDATE_ERROR);
        }
        return true;
    }

    @Override
    public Boolean deleteCommentByIds(Long[] commentIds) {
        List<Long> list = Arrays.stream(commentIds).collect(Collectors.toList());
        boolean b = this.removeBatchByIds(list);
        if (!b) {
            throw new BusinessException(ErrorCode.DATA_DELETE_ERROR);
        }
        return true;
    }
}




