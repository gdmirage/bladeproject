package com.blade.generator.controller;

import com.blade.generator.entity.TrackingNumberPool;
import com.blade.generator.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO:
 *
 * @author chenjiangxin
 * @date 2018/12/10 14:38
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/hello")
    public String hello() {
        return "hello spring-boot 2.0";
    }

    @GetMapping("/select")
    public TrackingNumberPool getTrackingNumberPool() {
        return testService.getTrackingNumberPool(1);
    }
}
