package com.backend.travel.controller;

import com.backend.travel.POJO.VO.picture.PictureListVo;
import com.backend.travel.common.BaseResponse;
import com.backend.travel.common.ResultUtils;
import com.backend.travel.service.impl.PictureServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping( "/picture" )
@RestController
public class PictureController {
    @Resource
    private PictureServiceImpl pictureService;

    @GetMapping( "/getPicture" )
    public BaseResponse<List<PictureListVo>> getPictureList(@RequestParam Long travelId) {
        List<PictureListVo> pictureListVos = pictureService.listPictureByTravelId(travelId);
        return ResultUtils.success(pictureListVos);
    }
}
