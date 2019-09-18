package com.blade.manager.system.modules.security;

import com.blade.manager.system.common.util.CaptchaUtil;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Base64;

/**
 * @author blade
 * 2019/9/18 14:13
 */
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @GetMapping("/getCaptcha")
    public String getCaptcha() {
        String captcha = CaptchaUtil.generateVerifyCode(4);
        ByteOutputStream outputStream = new ByteOutputStream();
        try {
            CaptchaUtil.drawImage(111, 36, outputStream, captcha);
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] bytes = Base64.getEncoder().encode(outputStream.getBytes());
        return new String(bytes);
    }
}
