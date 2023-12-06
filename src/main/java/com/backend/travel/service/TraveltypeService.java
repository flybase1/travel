package com.backend.travel.service;

import com.backend.travel.POJO.DTO.TravelTypeDto.TravelTypePageDto;
import com.backend.travel.POJO.entity.Traveltype;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author admin
* @description 针对表【traveltype(旅行项目)】的数据库操作Service
* @createDate 2023-12-03 10:25:33
*/
public interface TraveltypeService extends IService<Traveltype> {

    /**
     * 分页展示
     * @param travelTypePageDto
     * @return
     */
    Page<Traveltype> pageTravelType(TravelTypePageDto travelTypePageDto);
}
