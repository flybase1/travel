package com.backend.travel.controller;

import com.backend.travel.POJO.DTO.GuideDto.GuidePageDto;
import com.backend.travel.POJO.VO.guide.GuideInfoVo;
import com.backend.travel.POJO.VO.guide.GuidePageVo;
import com.backend.travel.POJO.VO.guide.GuideUpdateVo;
import com.backend.travel.common.BaseResponse;
import com.backend.travel.common.ResultUtils;
import com.backend.travel.service.impl.GuideServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RequestMapping( "/guide" )
@RestController
public class GuideController {
    @Resource
    private GuideServiceImpl guideService;

    /**
     * 分页展示导游信息
     *
     * @param guidePageDto
     * @return
     */
    @PostMapping( "/guidePage" )
    public BaseResponse<Page<GuidePageVo>> showGuidePage(@RequestBody GuidePageDto guidePageDto) {
        Page<GuidePageVo> guidePageVoPage = guideService.showGuidePage(guidePageDto);
        return ResultUtils.success(guidePageVoPage);
    }

    /**
     * 根据id获取guide信息
     * @param guideId
     * @return
     */
    @GetMapping( "/getGuideInfo" )
    public BaseResponse<GuideInfoVo> showGuideInfoById(@RequestParam Long guideId) {
        GuideInfoVo guideInfoVo = guideService.showGuideInfoById(guideId);
        return ResultUtils.success(guideInfoVo);
    }

    /**
     * 删除账号信息
     * @param guideId
     * @return
     */
    @DeleteMapping( "/deleteGuide" )
    public BaseResponse<Boolean> deleteGuideInfoById(@RequestParam Long guideId) {
        Boolean success = guideService.deleteGuideInfoById(guideId);
        return ResultUtils.success(success);
    }

    /**
     * 修改账号信息
     * @param guideUpdateVo
     * @return
     */
    @PutMapping( "/updateGuide" )
    public BaseResponse<Boolean> updateGuideInfo(@RequestBody GuideUpdateVo guideUpdateVo) {
        Boolean success = guideService.updateGuideInfo(guideUpdateVo);
        return ResultUtils.success(success);
    }
}
