package com.blade.manager.system.permission.service;

import com.blade.core.exception.ServiceException;
import com.blade.manager.system.permission.model.login.ImgResult;
import com.blade.manager.system.permission.model.login.LoginDTO;
import com.blade.manager.system.permission.model.login.LoginVO;

import java.io.IOException;

/**
 * TODO:
 *
 * @author blade
 * 2020/1/2 15:40
 */
public interface ILoginService {

    /**
     * 生成验证码
     *
     * @return {@link ImgResult}
     * @throws IOException 异常
     */
    ImgResult getCaptcha() throws IOException;

    /**
     * 登陆
     *
     * @param loginDTO {@link LoginDTO}
     * @return {@link LoginVO}
     * @throws ServiceException 业务异常
     */
    LoginVO login(LoginDTO loginDTO) throws ServiceException;

    /**
     * 退出
     *
     * @param token token
     */
    void logout(String token);
}
