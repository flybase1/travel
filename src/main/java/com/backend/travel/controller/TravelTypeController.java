package com.backend.travel.controller;

import cn.hutool.core.bean.BeanUtil;
import com.backend.travel.POJO.DTO.TravelTypeDto.TravelTypeAddDto;
import com.backend.travel.POJO.DTO.TravelTypeDto.TravelTypePageDto;
import com.backend.travel.POJO.DTO.TravelTypeDto.TravelTypeUpdateDto;
import com.backend.travel.POJO.entity.Traveltype;
import com.backend.travel.common.BaseResponse;
import com.backend.travel.common.ErrorCode;
import com.backend.travel.common.ResultUtils;
import com.backend.travel.execption.BusinessException;
import com.backend.travel.service.impl.TraveltypeServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 类型管理控制
 */
@RequestMapping( "/travelType" )
@RestController
public class TravelTypeController {
    @Resource
    private TraveltypeServiceImpl travelTypeService;

    /**
     * 列表展示所有的类型
     *
     * @return
     */
    @GetMapping( "/listType" )
    public BaseResponse<List<Traveltype>> listTravelType() {
        List<Traveltype> list = travelTypeService.list();
        return ResultUtils.success(list);
    }

    /**
     * 添加类型
     *
     * @param travelTypeAddDto
     * @return
     */
    @PostMapping( "/addType" )
    public BaseResponse<Boolean> addTravelType(@RequestBody TravelTypeAddDto travelTypeAddDto) {
        Traveltype traveltype = new Traveltype();
        String travelType = travelTypeAddDto.getTravelType();
        traveltype.setTravelType(travelType);
        boolean save = travelTypeService.save(traveltype);
        if (!save) {
            throw new BusinessException(ErrorCode.DATA_INSERT_ERROR);
        }
        return ResultUtils.success(true);
    }

    /**
     * 修改指定类型
     *
     * @param travelTypeUpdateDto
     * @return
     */
    @PutMapping( "/updateType" )
    public BaseResponse<Boolean> updateTravelType(@RequestBody TravelTypeUpdateDto travelTypeUpdateDto) {
        Traveltype traveltype = new Traveltype();
        BeanUtil.copyProperties(travelTypeUpdateDto, traveltype);
        boolean b = travelTypeService.updateById(traveltype);
        if (!b) {
            throw new BusinessException(ErrorCode.DATA_UPDATE_ERROR);
        }
        return ResultUtils.success(true);
    }

    /**
     * 删除指定类型
     *
     * @param typeId
     * @return
     */
    @DeleteMapping( "/deleteType/{typeId}" )
    public BaseResponse<Boolean> deleteTravelType(@PathVariable Integer typeId) {
        boolean b = travelTypeService.removeById(typeId);
        if (!b) {
            throw new BusinessException(ErrorCode.DATA_DELETE_ERROR);
        }
        return ResultUtils.success(true);
    }

    /**
     * 分页展示旅行项目分类
     *
     * @param travelTypePageDto
     * @return
     */
    @PostMapping( "/pageTravelType" )
    public BaseResponse<Page<Traveltype>> travelTypePage(@RequestBody TravelTypePageDto travelTypePageDto) {
        Page<Traveltype> traveltypePage = travelTypeService.pageTravelType(travelTypePageDto);
        return ResultUtils.success(traveltypePage);
    }

    /**
     * 获取指定类型的信息
     *
     * @param typeId
     * @return
     */
    @GetMapping( "/getTravelType/{typeId}" )
    public BaseResponse<Traveltype> getTravelType(@PathVariable Integer typeId) {
        Traveltype traveltype = travelTypeService.getOne(new QueryWrapper<Traveltype>().eq("typeId", typeId));
        return ResultUtils.success(traveltype);
    }
}
