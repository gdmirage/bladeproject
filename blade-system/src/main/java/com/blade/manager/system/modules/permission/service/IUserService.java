package com.blade.manager.system.modules.permission.service;

import com.blade.manager.system.modules.permission.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author blade
 * @since 2019-09-17
 */
public interface IUserService extends IService<User> {

    User getUserByNameOrEmail(String usernameOrEmail);

    User getUserByEmail(String email);

    User getUserByUsername(String username);
}
