package com.blade.manager.system.permission.service.impl;

import com.blade.core.page.PageHelper;
import com.blade.core.page.PageInfo;
import com.blade.core.service.impl.BaseServiceImpl;
import com.blade.manager.system.permission.entity.User;
import com.blade.manager.system.permission.mapper.UserMapper;
import com.blade.manager.system.permission.model.user.UserListVO;
import com.blade.manager.system.permission.model.user.UserPageSearchDTO;
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

    @Override
    public PageInfo<UserListVO> page(UserPageSearchDTO userPageSearchDTO) {
        PageInfo<UserListVO> pageInfo = PageHelper.startPage(userPageSearchDTO.getPageNumber(), userPageSearchDTO.getPageSize())
                .doSelectPageInfo(() -> super.baseMapper.selectPage(userPageSearchDTO));
        return pageInfo;
    }
}