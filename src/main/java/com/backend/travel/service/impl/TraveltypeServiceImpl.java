package com.backend.travel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.backend.travel.POJO.entity.Traveltype;
import com.backend.travel.service.TraveltypeService;
import com.backend.travel.dao.TraveltypeMapper;
import org.springframework.stereotype.Service;

/**
* @author admin
* @description 针对表【traveltype(旅行项目)】的数据库操作Service实现
* @createDate 2023-12-03 10:25:33
*/
@Service
public class TraveltypeServiceImpl extends ServiceImpl<TraveltypeMapper, Traveltype>
    implements TraveltypeService{

}




