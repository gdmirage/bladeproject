package com.blade.manager.system.modules.security.service;

import com.blade.manager.system.modules.permission.entity.User;
import com.blade.manager.system.modules.security.model.LoginDTO;

/**
 * 验证服务
 *
 * @author blade
 * 2019/9/19 16:46
 */
public interface IAuthenticationService {


    User login(LoginDTO loginDTO);
}
