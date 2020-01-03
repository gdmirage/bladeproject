package com.blade.manager.system.permission.mapper;

import com.blade.manager.system.permission.entity.User;
import com.blade.core.persistence.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Blade
 * @since 2019-12-20 16:08:17
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据邮箱查询用户
     * @param email 邮箱
     * @return {@link User}
     */
    User selectUserByEmail(@Param("email") String email);

    /**
     * 根据登陆名查询用户
     * @param loginName 登陆名
     * @return {@link User}
     */
    User selectUserByUsername(@Param("loginName") String loginName);
}