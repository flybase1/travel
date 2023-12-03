package com.backend.travel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.backend.travel.POJO.entity.Picture;
import com.backend.travel.service.PictureService;
import com.backend.travel.dao.PictureMapper;
import org.springframework.stereotype.Service;

/**
* @author admin
* @description 针对表【picture(图床地址)】的数据库操作Service实现
* @createDate 2023-12-03 10:25:22
*/
@Service
public class PictureServiceImpl extends ServiceImpl<PictureMapper, Picture>
    implements PictureService{

}




