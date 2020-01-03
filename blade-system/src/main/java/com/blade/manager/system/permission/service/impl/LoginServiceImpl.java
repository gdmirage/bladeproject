package com.blade.manager.system.permission.service.impl;

import com.blade.core.enums.ValidateResultCodeEnum;
import com.blade.core.exception.ServiceException;
import com.blade.core.model.base.JsonAble;
import com.blade.manager.system.permission.entity.User;
import com.blade.manager.system.permission.model.login.LoginDTO;
import com.blade.manager.system.permission.service.ILoginService;
import com.blade.manager.system.permission.service.IUserService;
import com.blade.starter.redis.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * TODO: 登陆服务
 *
 * @author Blade
 * @date 2020/1/2 15:41
 */
@Service("loginService")
public class LoginServiceImpl extends JsonAble implements ILoginService {

    @Autowired
    private IUserService userService;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public void login(LoginDTO loginDTO) throws ServiceException {
        if (StringUtils.isBlank(loginDTO.getLoginName())) {
            throw new ServiceException(ValidateResultCodeEnum.EMPTY_ACCOUNT);
        }

        if (StringUtils.isBlank(loginDTO.getPassword())) {
            throw new ServiceException(ValidateResultCodeEnum.EMPTY_PASSWORD);
        }

        if (StringUtils.isBlank(loginDTO.getCode())) {
            throw new ServiceException(ValidateResultCodeEnum.WRONG_CAPTCHA);
        }

        User user = this.userService.findUserByLoginNameOrEmail(loginDTO.getLoginName());

        if (null == user) {
            throw new ServiceException(ValidateResultCodeEnum.WRONG_ACCOUNT_OR_PASSWORD);
        }

        if (!Objects.equals(user.getPassword(), loginDTO.getPassword())) {
            throw new ServiceException(ValidateResultCodeEnum.WRONG_ACCOUNT_OR_PASSWORD);
        }

        super.logger.debug(user.toString());
        throw new ServiceException(ValidateResultCodeEnum.WRONG_CAPTCHA);
    }
}
