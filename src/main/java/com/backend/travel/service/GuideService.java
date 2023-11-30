package com.backend.travel.service;

import com.backend.travel.POJO.DTO.GuideDto.GuidePageDto;
import com.backend.travel.POJO.VO.guide.GuideInfoVo;
import com.backend.travel.POJO.VO.guide.GuidePageVo;
import com.backend.travel.POJO.VO.guide.GuideUpdateVo;
import com.backend.travel.POJO.entity.Guide;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author admin
* @description 针对表【guide(导游表)】的数据库操作Service
* @createDate 2023-11-30 15:47:17
*/
public interface GuideService extends IService<Guide> {

    /**
     * 分页展示
     * @param guidePageDto
     * @return
     */
    Page<GuidePageVo> showGuidePage(GuidePageDto guidePageDto);

    /**
     * 根据id获取guide
     * @param guideId
     * @return
     */
    GuideInfoVo showGuideInfoById(Long guideId);

    /**
     * 删除guide
     * @param guideId
     * @return
     */
    Boolean deleteGuideInfoById(Long guideId);

    /**
     * 更新导游信息
     * @param guideUpdateVo
     * @return
     */
    Boolean updateGuideInfo(GuideUpdateVo guideUpdateVo);
}
