package com.blade.manager.system.modules.permission.mapper;

import com.blade.manager.system.modules.permission.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author blade
 * @since 2019-09-17
 */
public interface UserMapper extends BaseMapper<User> {

    User selectUserByEmail(@Param("email") String email);

    User selectUserByUsername(@Param("username") String username);
}
