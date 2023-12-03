package com.backend.travel.service;

import com.backend.travel.POJO.DTO.Travel.TravelPageDto;
import com.backend.travel.POJO.VO.travel.TravelPageVo;
import com.backend.travel.POJO.VO.travel.TravelVo;
import com.backend.travel.POJO.entity.Travel;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author admin
* @description 针对表【travel(旅游表)】的数据库操作Service
* @createDate 2023-12-03 09:59:36
*/
public interface TravelService extends IService<Travel> {

    /**
     * 分页展示项目
     * @param travelPageDto
     * @return
     */
    Page<TravelPageVo> getTravelPage(TravelPageDto travelPageDto);

    /**
     * 获取指定项目id
     * @param travelId
     * @return
     */
    TravelVo getTravelByTravelId(Long travelId);
}
