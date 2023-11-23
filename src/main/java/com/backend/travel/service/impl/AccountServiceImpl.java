package com.backend.travel.service.impl;

import com.backend.travel.POJO.entity.Account;
import com.backend.travel.dao.AccountMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.backend.travel.service.AccountService;
import org.springframework.stereotype.Service;

/**
* @author admin
* @description 针对表【account(账号表)】的数据库操作Service实现
* @createDate 2023-11-23 10:52:26
*/
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account>
        implements AccountService{

}




