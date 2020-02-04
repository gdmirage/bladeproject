package com.blade.manager.system.permission.mapper;

import com.blade.core.persistence.mapper.BaseMapper;
import com.blade.manager.system.permission.entity.UserRoles;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Blade
 * @since 2019-12-20 16:08:17
 */
public interface UserRolesMapper extends BaseMapper<UserRoles> {

    /**
     * 根据用户ID获取角色ID列表
     *
     * @param userId user id
     * @return role ids
     */
    List<Long> selectRoleIdsByUserId(Long userId);
}