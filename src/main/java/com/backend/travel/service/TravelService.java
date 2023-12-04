package com.backend.travel.service;

import com.backend.travel.POJO.DTO.Travel.TravelAddDto;
import com.backend.travel.POJO.DTO.Travel.TravelPageDto;
import com.backend.travel.POJO.DTO.Travel.TravelUpdateDto;
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

    /**
     * 添加旅游项目
     * @param travelAddDto
     * @return
     */
    Boolean addTrave(TravelAddDto travelAddDto);

    /**
     * 修改旅游项目
     * @param travelUpdateDto
     * @return
     */
    Boolean updateTravel(TravelUpdateDto travelUpdateDto);

    /**
     * 删除指定旅游项目
     * @param travelId
     * @return
     */
    Boolean deleteTravelById(Long travelId);

    /**
     * 删除指定列表旅游项目
     * @param travelIds
     * @return
     */
    Boolean deleteTravels(Long[] travelIds);

    /**
     * 上线旅游项目
     * @param travelId
     * @return
     */
    Boolean onlineProject(Long travelId);

    /**
     * 下线旅游项目
     * @param travelId
     * @return
     */
    Boolean offlineProject(Long travelId);
}
