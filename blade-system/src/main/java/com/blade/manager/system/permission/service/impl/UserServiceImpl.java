package com.blade.manager.system.permission.service.impl;

import com.blade.manager.system.permission.service.IUserService;
import org.springframework.stereotype.Service;
import com.blade.manager.system.permission.entity.User;
import com.blade.manager.system.permission.mapper.UserMapper;
import com.blade.core.service.impl.BaseServiceImpl;
/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Blade
 * @since 2019-12-20 16:08:17
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements IUserService {

}