package com.blade.manager.system.modules.permission.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blade.manager.system.modules.permission.entity.User;
import com.blade.manager.system.modules.permission.model.user.UserListVO;
import com.blade.manager.system.modules.permission.model.user.UserPageSearchDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author blade
 * @since 2019-09-17
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据邮箱查询用户
     *
     * @param email email
     * @return {@link User}
     */
    User selectUserByEmail(@Param("email") String email);

    /**
     * 根据登陆名查询用户
     *
     * @param username 登陆名
     * @return {@link User}
     */
    User selectUserByUsername(@Param("username") String username);

    /**
     * 分页查询
     *
     * @param userPageSearchDTO {@link UserPageSearchDTO}
     * @return {@link List<User>}
     */
    List<UserListVO> selectPage(@Param("searchDTO") UserPageSearchDTO userPageSearchDTO);
}
