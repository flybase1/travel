package com.backend.travel.service;

import com.backend.travel.POJO.VO.picture.PictureListVo;
import com.backend.travel.POJO.entity.Picture;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author admin
* @description 针对表【picture(图床地址)】的数据库操作Service
* @createDate 2023-12-03 10:25:22
*/
public interface PictureService extends IService<Picture> {

    /**
     * 获取展示的照片
     * @param travelId
     * @return
     */
    List<PictureListVo> listPictureByTravelId(Long travelId);
}
