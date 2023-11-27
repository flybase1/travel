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
    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    void contextLoads() {
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        String encode = bCryptPasswordEncoder.encode("123456");
//        System.out.println(encode);
        String pwd = "$2a$10$8IthMjNdrXU8.JjcTR1F6uuDVDV.yaus2Hnhr8qyrd1QVKjvNRsom";
        boolean matches = bCryptPasswordEncoder.matches("222222", pwd);
        System.out.println(matches);

    }

    @Test
    void name() {
        Account test = accountService.getByUserName("admin");
        System.out.println(test);
    }
}
