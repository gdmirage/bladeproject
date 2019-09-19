package com.blade.manager.system.modules.security.service.impl;

import com.blade.manager.system.common.service.IRedisService;
import com.blade.manager.system.modules.permission.entity.User;
import com.blade.manager.system.modules.permission.service.IUserService;
import com.blade.manager.system.modules.security.model.LoginDTO;
import com.blade.manager.system.modules.security.service.IAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author blade
 * 2019/9/19 16:46
 */
@Service("authenticationService")
public class AuthenticationImpl implements IAuthenticationService {

    @Autowired
    private IRedisService redisService;

    @Autowired
    private IUserService userService;

    @Override
    public User login(LoginDTO loginDTO) {
        String captcha = redisService.getCaptcha(loginDTO.getUuid());
        if (!Objects.equals(captcha, loginDTO.getCaptcha())) {
            System.out.println("验证码错误");
            return null;
        }

        User user;

        user = userService.getUserByEmail(loginDTO.getUsername());
        if (null == user) {
            user = userService.getUserByUsername(loginDTO.getUsername());
        }

        if (null == user) {
            System.out.println("查无用户");
            return null;
        }

        System.out.println("有用户");
        return user;
    }
}
