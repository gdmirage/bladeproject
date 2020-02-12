package com.blade.manager.system.permission.service.impl;

import com.blade.core.enums.CommonResultCodeEnum;
import com.blade.core.enums.ValidateResultCodeEnum;
import com.blade.core.exception.ServiceException;
import com.blade.core.page.PageHelper;
import com.blade.core.page.PageInfo;
import com.blade.core.service.impl.BaseServiceImpl;
import com.blade.manager.system.permission.entity.User;
import com.blade.manager.system.permission.entity.UserRoles;
import com.blade.manager.system.permission.mapper.UserMapper;
import com.blade.manager.system.permission.model.user.UserInsertOrUpdateDTO;
import com.blade.manager.system.permission.model.user.UserListVO;
import com.blade.manager.system.permission.model.user.UserPageSearchDTO;
import com.blade.manager.system.permission.service.IUserRolesService;
import com.blade.manager.system.permission.service.IUserService;
import com.blade.util.EncryptUtils;
import org.apache.commons.codec.cli.Digest;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.security.provider.MD5;

import java.util.List;

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

    @Autowired
    private IUserRolesService userRolesService;

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

        pageInfo.getRecordList().forEach(userListVO -> {
            userListVO.setRoleIds(this.userRolesService.getRoleIdsByUserId(userListVO.getId()));
        });

        return pageInfo;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insert(UserInsertOrUpdateDTO userInsertOrUpdateDTO) {
        User user = new User();
        BeanUtils.copyProperties(userInsertOrUpdateDTO, user);
        user.setPassword(DigestUtils.md5Hex("123456"));
        super.baseMapper.insert(user);
        this.insertUserRoles(user.getId(), userInsertOrUpdateDTO.getRoleIds());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(UserInsertOrUpdateDTO userInsertOrUpdateDTO) {
        User user = new User();
        BeanUtils.copyProperties(userInsertOrUpdateDTO, user);
        this.baseMapper.update(user);
        this.insertUserRoles(user.getId(), userInsertOrUpdateDTO.getRoleIds());
    }

    private void insertUserRoles(Long userId, List<Long> roleIds) {

        this.userRolesService.deleteByUserId(userId);

        roleIds.forEach(roleId -> {
            UserRoles userRoles = new UserRoles();
            userRoles.setUserId(userId);
            userRoles.setRoleId(roleId);
            this.userRolesService.insert(userRoles);
        });
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Long userId) {
        this.userRolesService.deleteByUserId(userId);
        super.baseMapper.deleteByPk(userId);
    }
}