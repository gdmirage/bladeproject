package com.blade.manager.system.permission.service;

import com.blade.core.page.PageInfo;
import com.blade.core.service.IBaseService;
import com.blade.manager.system.permission.entity.User;
import com.blade.manager.system.permission.model.user.UserInsertOrUpdateDTO;
import com.blade.manager.system.permission.model.user.UserListVO;
import com.blade.manager.system.permission.model.user.UserPageSearchDTO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Blade
 * @since 2019-12-20 16:08:17
 */
public interface IUserService extends IBaseService<User> {

    /**
     * 根据登陆账号或邮箱查询用户
     *
     * @param loginName
     * @return {@link User}
     */
    User findUserByLoginNameOrEmail(String loginName);

    /**
     * 列表分页查询
     *
     * @param userPageSearchDTO {@link UserPageSearchDTO}
     * @return {@link PageInfo<UserListVO>}
     */
    PageInfo<UserListVO> page(UserPageSearchDTO userPageSearchDTO);

    /**
     * 新增
     *
     * @param userInsertOrUpdateDTO {@link UserInsertOrUpdateDTO}
     */
    void insert(UserInsertOrUpdateDTO userInsertOrUpdateDTO);

    /**
     * 更新
     *
     * @param userInsertOrUpdateDTO {@link UserInsertOrUpdateDTO}
     */
    void update(UserInsertOrUpdateDTO userInsertOrUpdateDTO);

    /**
     * 删除
     *
     * @param userId 用户ID
     */
    void delete(Long userId);
}