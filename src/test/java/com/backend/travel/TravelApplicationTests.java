package com.backend.travel;

import com.backend.travel.POJO.entity.Account;
import com.backend.travel.service.AccountService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

@SpringBootTest
class TravelApplicationTests {
    @Resource
    private AccountService accountService;

    @Test
    void contextLoads() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode("123456");
        System.out.println(encode);
    }

    @Test
    void name() {
        Account test = accountService.getByUserName("admin");
        System.out.println(test);
    }
}
