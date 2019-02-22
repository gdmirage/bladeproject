package com.blade.manager.system.controller;

import com.blade.manager.system.common.persistence.entity.SysUser;
import com.blade.manager.system.modules.sys.service.ISysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * TODO:
 *
 * @author chenjiangxin
 * @date 2018/12/10 14:38
 */
@RestController
@RequestMapping("/test")
public class TestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private ISysUserService sysUserService;

    @GetMapping("/hello")
    public String hello() {
        return "hello spring-boot 2.0";
    }

    @GetMapping("/select")
    public SysUser getTrackingNumberPool() {
//        return sysUserService.selectById(1);
        return null;
    }

    @GetMapping("/getIp")
    public String getIp(HttpServletRequest request) {
        String remoteAddr = request.getRemoteAddr();
        LOGGER.info("remoteAddr = {}", remoteAddr);
        String nginxIp = request.getHeader("X-Real_IP");
        String version = request.getHeader("interface_version");
        return remoteAddr + " -> " + nginxIp + " -> " + version;
    }
}
