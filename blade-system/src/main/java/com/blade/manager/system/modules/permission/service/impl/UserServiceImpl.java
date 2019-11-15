package com.blade.manager.system.modules.permission.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blade.manager.system.modules.permission.entity.User;
import com.blade.manager.system.modules.permission.entity.UsersRoles;
import com.blade.manager.system.modules.permission.mapper.UserMapper;
import com.blade.manager.system.modules.permission.model.role.RoleListVO;
import com.blade.manager.system.modules.permission.model.role.RolePageSearchDTO;
import com.blade.manager.system.modules.permission.model.role.RoleVO;
import com.blade.manager.system.modules.permission.model.user.UserInsertOrUpdateDTO;
import com.blade.manager.system.modules.permission.model.user.UserListVO;
import com.blade.manager.system.modules.permission.model.user.UserPageSearchDTO;
import com.blade.manager.system.modules.permission.service.IRoleService;
import com.blade.manager.system.modules.permission.service.IUserService;
import com.blade.manager.system.modules.permission.service.IUsersRolesService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private IUsersRolesService usersRolesService;

    @Autowired
    private IRoleService roleService;

    @Override
    public User getUserByNameOrEmail(String usernameOrEmail) {
        User user = this.getUserByEmail(usernameOrEmail);
        if (null == user) {
            user = this.getUserByUsername(usernameOrEmail);
        }

        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        return baseMapper.selectUserByEmail(email);
    }

    @Override
    public User getUserByUsername(String username) {
        return baseMapper.selectUserByUsername(username);
    }

    @Override
    public PageInfo<UserListVO> page(UserPageSearchDTO userPageSearchDTO) {

        PageInfo<UserListVO> pageInfo = PageHelper.startPage(userPageSearchDTO.getPageNum(), userPageSearchDTO.getPageSize())
                .doSelectPageInfo(() -> {
                            super.baseMapper.selectPage(userPageSearchDTO);
                        }
                );

        pageInfo.getList().forEach(userListVO -> {
            userListVO.setRoles(this.roleService.getRolesByUserId(userListVO.getId()));
        });

        return pageInfo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(UserInsertOrUpdateDTO userInsertOrUpdateDTO) {
        User user = new User();
        BeanUtils.copyProperties(userInsertOrUpdateDTO, user);
        user.setEnabled(userInsertOrUpdateDTO.getEnabled() ? 1L : 0L);

        // 默认信息
        user.setAvatar("https://i.loli.net/2019/04/04/5ca5b971e1548.jpeg");
        // 密码123456
        user.setPassword("e10adc3949ba59abbe56e057f20f883e");
        super.save(user);

        userInsertOrUpdateDTO.getRoleIds().forEach(roleId -> {
            UsersRoles usersRoles = new UsersRoles();
            usersRoles.setRoleId(roleId);
            usersRoles.setUserId(user.getId());
            this.usersRolesService.save(usersRoles);
        });
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(UserInsertOrUpdateDTO userInsertOrUpdateDTO) {
        User user = new User();
        BeanUtils.copyProperties(userInsertOrUpdateDTO, user);

        user.setEnabled(userInsertOrUpdateDTO.getEnabled() ? 1L : 0L);

        super.updateById(user);

        this.usersRolesService.deleteByUserId(userInsertOrUpdateDTO.getId());

        userInsertOrUpdateDTO.getRoleIds().forEach(roleId -> {
            UsersRoles usersRoles = new UsersRoles();
            usersRoles.setRoleId(roleId);
            usersRoles.setUserId(user.getId());
            this.usersRolesService.save(usersRoles);
        });
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long userId) {
        this.usersRolesService.deleteByUserId(userId);
        super.removeById(userId);
    }
}
