package com.backend.travel.service;

import com.backend.travel.POJO.DTO.CustomedTravelDto.CustomizedTravelPageDto;
import com.backend.travel.POJO.entity.Customizedtravel;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author admin
* @description 针对表【customizedtravel(用户定制旅游)】的数据库操作Service
* @createDate 2023-12-15 10:11:34
*/
public interface CustomizedtravelService extends IService<Customizedtravel> {
    /**
     * 分页展示定制旅游信息
     * @param customizedTravelPageDto
     * @return
     */
    Page<Customizedtravel> pageCustomizedTravel(CustomizedTravelPageDto customizedTravelPageDto);
}
