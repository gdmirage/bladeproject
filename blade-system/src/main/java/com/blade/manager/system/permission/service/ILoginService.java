package com.blade.manager.system.permission.service;

import com.blade.core.exception.ServiceException;
import com.blade.manager.system.permission.model.login.LoginDTO;

/**
 * TODO:
 *
 * @author blade
 * 2020/1/2 15:40
 */
public interface ILoginService {

    /**
     * 登陆
     * @param loginDTO {@link LoginDTO}
     * @throws ServiceException 业务异常
     */
    void login(LoginDTO loginDTO) throws ServiceException;
}
