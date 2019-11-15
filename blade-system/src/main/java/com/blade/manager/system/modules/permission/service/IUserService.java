package com.blade.manager.system.modules.permission.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blade.manager.system.modules.permission.entity.User;
import com.blade.manager.system.modules.permission.model.role.RoleListVO;
import com.blade.manager.system.modules.permission.model.user.UserInsertOrUpdateDTO;
import com.blade.manager.system.modules.permission.model.user.UserListVO;
import com.blade.manager.system.modules.permission.model.user.UserPageSearchDTO;
import com.github.pagehelper.PageInfo;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author blade
 * @since 2019-09-17
 */
public interface IUserService extends IService<User> {

    /**
     * 根据登陆名或者邮箱查询用户
     *
     * @param usernameOrEmail usernameOrEmail
     * @return {@link User}
     */
    User getUserByNameOrEmail(String usernameOrEmail);

    /**
     * 根据邮箱查询用户
     *
     * @param email email
     * @return {@link User}
     */
    User getUserByEmail(String email);

    /**
     * 根据登陆名查询用户
     *
     * @param username username
     * @return {@link User}
     */
    User getUserByUsername(String username);

    /**
     * 分页查询
     *
     * @param userPageSearchDTO {@link UserPageSearchDTO}
     * @return {@link PageInfo<RoleListVO>}
     */
    PageInfo<UserListVO> page(UserPageSearchDTO userPageSearchDTO);

    /**
     * 新增
     *
     * @param userInsertOrUpdateDTO {@link UserInsertOrUpdateDTO}
     */
    void save(UserInsertOrUpdateDTO userInsertOrUpdateDTO);

    /**
     * 修改
     *
     * @param userInsertOrUpdateDTO {@link UserInsertOrUpdateDTO}
     */
    void update(UserInsertOrUpdateDTO userInsertOrUpdateDTO);

    /**
     * 删除
     *
     * @param userId
     */
    void delete(Long userId);
}
