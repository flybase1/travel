package com.backend.travel.service.impl;

import com.backend.travel.POJO.DTO.AccountPageDto;
import com.backend.travel.POJO.VO.AccountPageVo;
import com.backend.travel.POJO.entity.Account;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountServiceImplTest {
    @Resource
    private AccountServiceImpl accountService;

    @Test
    void getByUserName() {
        Account admin = accountService.getByUserName("admin");
        System.out.println(admin.getUserAccount());
    }

    @Test
    void getUserAuthorityInfo() {
    }

    @Test
    void getAccountInfoPage() {
        AccountPageDto accountPageDto = new AccountPageDto();
        accountPageDto.setCurrent(1);
        accountPageDto.setQueryRoleId(1);
        accountPageDto.setPageSize(10);
        Page<AccountPageVo> accountInfoPage = accountService.getAccountInfoPage(accountPageDto);
        List<AccountPageVo> list =
                accountInfoPage.getRecords();
        list.stream().forEach(System.out::println);
    }
}