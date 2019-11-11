package com.blade.manager.system.modules.security.service.impl;

import com.blade.manager.system.common.service.IRedisService;
import com.blade.manager.system.common.util.CaptchaUtil;
import com.blade.manager.system.modules.permission.entity.User;
import com.blade.manager.system.modules.permission.service.IRolesPermissionsService;
import com.blade.manager.system.modules.permission.service.IUserService;
import com.blade.manager.system.modules.security.model.AuthenticationInfo;
import com.blade.manager.system.modules.security.model.LoginDTO;
import com.blade.manager.system.modules.security.model.LoginUser;
import com.blade.manager.system.modules.security.service.IAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
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

    @Autowired
    private IRolesPermissionsService rolesPermissionsService;

    @Override
    public AuthenticationInfo login(LoginDTO loginDTO) {
        /*String captcha = redisService.getCaptcha(loginDTO.getUuid());

        if (StringUtils.isEmpty(captcha)) {
            System.out.println("验证码错误");
            return null;
        }

        if (!captcha.equalsIgnoreCase(loginDTO.getCaptcha())) {
            System.out.println("验证码错误");
            return null;
        }*/

        User user = userService.getUserByNameOrEmail(loginDTO.getUsername());
        if (null == user) {
            System.out.println("用户不存在");
            return null;
        }

        if (!Objects.equals(user.getPassword(), loginDTO.getPassword())) {
            System.out.println("密码错误");
            return null;
        }

        String token = CaptchaUtil.generateVerifyCode(64);

        List<String> permissions = rolesPermissionsService.selectUserPermissionsByUserId(user.getId());

        LoginUser loginUser = new LoginUser(user.getUsername(), user.getAvatar(), user.getEmail(),
                user.getPhone(), String.valueOf(user.getDeptId()), String.valueOf(user.getJobId()));
        loginUser.setRoles(permissions);

        return new AuthenticationInfo(token, loginUser);
    }
}
