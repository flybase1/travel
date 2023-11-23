package com.backend.travel.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping( "/test" )
@RestController
public class TestController {

    @GetMapping( "/test" )
    public String hello() {
        return "hello";
    }
}
