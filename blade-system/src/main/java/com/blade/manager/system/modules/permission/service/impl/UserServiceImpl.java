package com.blade.manager.system.modules.permission.service.impl;

import com.blade.manager.system.modules.permission.entity.User;
import com.blade.manager.system.modules.permission.mapper.UserMapper;
import com.blade.manager.system.modules.permission.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blade.manager.system.modules.security.model.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author blade
 * @since 2019-09-17
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    public User getUserByEmail(String email) {
        return baseMapper.selectUserByEmail(email);
    }

    public User getUserByUsername(String username) {
        return baseMapper.selectUserByUsername(username);
    }
}
