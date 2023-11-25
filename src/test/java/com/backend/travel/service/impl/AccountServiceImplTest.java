package com.backend.travel.service.impl;

import com.backend.travel.POJO.entity.Account;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

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
}