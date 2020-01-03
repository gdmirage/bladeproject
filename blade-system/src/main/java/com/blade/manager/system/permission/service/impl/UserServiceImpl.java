package com.blade.manager.system.permission.service.impl;

import com.blade.core.service.impl.BaseServiceImpl;
import com.blade.manager.system.permission.entity.User;
import com.blade.manager.system.permission.mapper.UserMapper;
import com.blade.manager.system.permission.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Blade
 * @since 2019-12-20 16:08:17
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public User findUserByLoginNameOrEmail(String loginName) {
        User user = super.baseMapper.selectUserByUsername(loginName);

        if (null == user) {
            user = super.baseMapper.selectUserByEmail(loginName);
        }

        return user;
    }
}