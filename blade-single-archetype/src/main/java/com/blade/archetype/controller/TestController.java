package com.blade.archetype.controller;

import com.blade.archetype.entity.TrackingNumberPool;
import com.blade.archetype.entity.User;
import com.blade.archetype.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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

    @GetMapping("/npe")
    public String exception() {
        throw new NullPointerException("就是我抛的");
    }

    @PostMapping("/user/save")
    public User save(@Valid @RequestBody User user) {
        return user;
    }
}
