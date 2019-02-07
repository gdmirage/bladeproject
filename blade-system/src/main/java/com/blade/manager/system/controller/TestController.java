package com.blade.manager.system.controller;

import com.blade.manager.system.modules.sys.entity.SysUser;
import com.blade.manager.system.modules.sys.service.ISysUserService;
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
    private ISysUserService sysUserService;

    @GetMapping("/hello")
    public String hello() {
        return "hello spring-boot 2.0";
    }

    @GetMapping("/select")
    public SysUser getTrackingNumberPool() {
        return sysUserService.selectById(1);
    }
}
