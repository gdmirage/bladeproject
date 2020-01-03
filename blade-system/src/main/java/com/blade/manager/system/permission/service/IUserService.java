package com.blade.manager.system.permission.service;

import com.blade.manager.system.permission.entity.User;
import com.blade.core.service.IBaseService;
/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Blade
 * @since 2019-12-20 16:08:17
 */
public interface IUserService extends IBaseService<User> {

    /**
     * 根据登陆账号或邮箱查询用户
     * @param loginName
     * @return {@link User}
     */
    User findUserByLoginNameOrEmail(String loginName);
}