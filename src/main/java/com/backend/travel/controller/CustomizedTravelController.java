package com.backend.travel.controller;

import com.backend.travel.POJO.DTO.CustomedTravelDto.CustomizedTravelPageDto;
import com.backend.travel.POJO.entity.Customizedtravel;
import com.backend.travel.common.BaseResponse;
import com.backend.travel.common.ResultUtils;
import com.backend.travel.service.impl.CustomizedtravelServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/customizedTravel")
public class CustomizedTravelController {
    @Resource
    private CustomizedtravelServiceImpl customizedtravelService;

    /**
     * 分页获取用户定制旅游
     * @param customizedTravelPageDto
     * @return
     */
    @PostMapping("/pageCustomizedTravel")
    public BaseResponse<Page<Customizedtravel>> pageCustomizedTravel(@RequestBody CustomizedTravelPageDto customizedTravelPageDto) {
        Page<Customizedtravel> page = customizedtravelService.pageCustomizedTravel(customizedTravelPageDto);
        return ResultUtils.success(page);
    }

    /**
     * 获取详细信息
     * @param id
     * @return
     */
    @GetMapping("/getInfo")
    public BaseResponse<Customizedtravel> getCustomizedTravel(@RequestParam("id") Long id) {
        Customizedtravel customizedTravel = customizedtravelService.getOne(new QueryWrapper<Customizedtravel>().eq("customizedTravelId", id));
        return ResultUtils.success(customizedTravel);
    }
}
