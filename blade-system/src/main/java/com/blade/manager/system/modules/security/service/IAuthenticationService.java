package com.blade.manager.system.modules.security.service;

import com.blade.manager.system.modules.security.model.LoginDTO;

/**
 * 验证服务
 *
 * @author blade
 * 2019/9/19 16:46
 */
public interface IAuthenticationService {


    void login(LoginDTO loginDTO);
}
