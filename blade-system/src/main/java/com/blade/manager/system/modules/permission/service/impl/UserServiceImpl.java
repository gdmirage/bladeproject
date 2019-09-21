package com.blade.manager.system.modules.permission.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blade.manager.system.modules.permission.entity.User;
import com.blade.manager.system.modules.permission.mapper.UserMapper;
import com.blade.manager.system.modules.permission.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户服务实现类
 * </p>
 *
 * @author blade
 * @since 2019-09-17
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    public User getUserByNameOrEmail(String usernameOrEmail) {
        User user = this.getUserByEmail(usernameOrEmail);
        if (null == user) {
            user = this.getUserByUsername(usernameOrEmail);
        }

        return user;
    }

    public User getUserByEmail(String email) {
        return baseMapper.selectUserByEmail(email);
    }

    public User getUserByUsername(String username) {
        return baseMapper.selectUserByUsername(username);
    }
}
