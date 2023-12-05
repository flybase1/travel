package com.backend.travel.controller;

import com.backend.travel.POJO.VO.picture.PictureListVo;
import com.backend.travel.common.BaseResponse;
import com.backend.travel.common.ErrorCode;
import com.backend.travel.common.ResultUtils;
import com.backend.travel.execption.BusinessException;
import com.backend.travel.service.impl.PictureServiceImpl;
import com.backend.travel.utils.ImageUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RequestMapping( "/picture" )
@RestController
public class PictureController {
    @Resource
    private PictureServiceImpl pictureService;
    @Resource
    private ImageUtils imageUtils;

    /**
     * 获取travel-picture关联图片
     *
     * @param travelId
     * @return
     */
    @GetMapping( "/getPicture" )
    public BaseResponse<List<PictureListVo>> getPictureList(@RequestParam Long travelId) {
        List<PictureListVo> pictureListVos = pictureService.listPictureByTravelId(travelId);
        return ResultUtils.success(pictureListVos);
    }


    /**
     * 上传图片到七牛云，和当前picture-travel无关
     * 单文件上传
     *
     * @param multipartFile
     * @return
     */
    @PostMapping( "/upload" )
    public BaseResponse<String> uploadImage(@RequestParam( value = "file", required = false ) MultipartFile multipartFile) {
        if (ObjectUtils.isEmpty(multipartFile)) {
            throw new BusinessException(ErrorCode.PARAMS_EMPTY_ERROR, "请选择文件");
        }
        String url = imageUtils.uploadImageQiniu(multipartFile);
        return ResultUtils.success(url);
    }

    /**
     * 多文件上传
     *
     * @param multipartFile
     * @return
     */
    @PostMapping( "/uploadFiles" )
    public BaseResponse<Map<String, List<String>>> uploadImage(@RequestParam( value = "file", required = false ) MultipartFile[] multipartFile) {
        if (ObjectUtils.isEmpty(multipartFile)) {
            throw new BusinessException(ErrorCode.PARAMS_EMPTY_ERROR, "请选择文件");
        }
        Map<String, List<String>> map = imageUtils.uploadImages(multipartFile);
        return ResultUtils.success(map);
    }
}
