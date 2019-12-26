package com.blade.manager.system.permission.controller;

import com.blade.manager.system.permission.model.login.ImgResult;
import com.blade.starter.redis.RedisUtils;
import com.blade.util.CaptchaUtil;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.apache.commons.codec.Charsets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

/**
 * 登陆控制器
 *
 * @author blade
 * 2019/12/20 17:21
 */
@RestController
@RequestMapping("/permission/login")
public class LoginController {

    private RedisUtils redisUtils;

    @Autowired
    public LoginController(RedisUtils redisUtils) {
        this.redisUtils = redisUtils;
    }

    @GetMapping("/getCaptcha")
    public ImgResult getCaptcha() throws IOException {
        String captcha = CaptchaUtil.generateVerifyCode(4);
        ByteOutputStream outputStream = new ByteOutputStream();
        String uuid = UUID.randomUUID().toString();
        redisUtils.save(uuid, captcha);
        CaptchaUtil.drawImage(111, 36, outputStream, captcha);
        byte[] bytes = Base64.getEncoder().encode(outputStream.getBytes());
        return new ImgResult("data:image/gif;base64," + new String(bytes, Charsets.UTF_8.name()), uuid);
    }


}