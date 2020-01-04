package com.blade.manager.system.permission.api;

import com.blade.manager.system.common.CommonController;
import com.blade.manager.system.constant.Constants;
import com.blade.manager.system.permission.model.login.ImgResult;
import com.blade.manager.system.permission.model.login.LoginDTO;
import com.blade.manager.system.permission.model.login.LoginVO;
import com.blade.manager.system.permission.service.ILoginService;
import com.blade.starter.redis.RedisUtils;
import com.blade.util.CaptchaUtil;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.apache.commons.codec.Charsets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;
import java.util.UUID;


/**
 * 登陆控制器
 *
 * @author blade
 * 2019/12/20 17:21
 */
@RestController("ApiLoginController")
@RequestMapping("/api/permission/login")
public class LoginController extends CommonController {

    private static final long serialVersionUID = 2884011829907135961L;

    private ILoginService loginService;

    @Autowired
    public LoginController(ILoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/getCaptcha")
    public ImgResult getCaptcha() throws Exception {
        String captcha = CaptchaUtil.generateVerifyCode(4);
        ByteOutputStream outputStream = new ByteOutputStream();
        String uuid = UUID.randomUUID().toString();
        super.redisUtils.save(uuid, captcha, Constants.Cache.CAPTCHA_EXPIRE_TIME);
        CaptchaUtil.drawImage(111, 36, outputStream, captcha);
        byte[] bytes = Base64.getEncoder().encode(outputStream.getBytes());
        return new ImgResult("data:image/gif;base64," + new String(bytes, Charsets.UTF_8.name()), uuid);
    }

    @PostMapping("/login")
    public LoginVO login(@RequestBody LoginDTO loginDTO) throws Exception {
        return loginService.login(loginDTO);
    }
}
