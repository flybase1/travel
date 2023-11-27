package com.backend.travel.controller;

import com.backend.travel.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping( "/user" )
@RestController
public class UserController {
    @Resource
    private UserServiceImpl userService;
}
