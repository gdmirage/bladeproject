package com.blade.manager.system.modules.security;

import com.alibaba.fastjson.JSON;
import com.blade.manager.system.common.service.IRedisService;
import com.blade.manager.system.common.util.CaptchaUtil;
import com.blade.manager.system.modules.security.model.ImgResult;
import com.blade.manager.system.modules.security.model.LoginDTO;
import com.blade.manager.system.modules.security.service.IAuthenticationService;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

/**
 * @author blade
 * 2019/9/18 14:13
 */
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private IRedisService redisService;

    @Autowired
    private IAuthenticationService authenticationService;

    @GetMapping("/getCaptcha")
    public ImgResult getCaptcha() {
        String captcha = CaptchaUtil.generateVerifyCode(4);
        ByteOutputStream outputStream = new ByteOutputStream();
        String uuid = UUID.randomUUID().toString();
        try {
            System.out.println(uuid + "----" + captcha);
            redisService.saveCaptcha(uuid, captcha);
            CaptchaUtil.drawImage(111, 36, outputStream, captcha);
            System.out.println(redisService.getCaptcha(uuid));
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] bytes = Base64.getEncoder().encode(outputStream.getBytes());
        return new ImgResult(new String(bytes), uuid);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDTO loginDTO) {
        authenticationService.login(loginDTO);
        return null;
    }
}
