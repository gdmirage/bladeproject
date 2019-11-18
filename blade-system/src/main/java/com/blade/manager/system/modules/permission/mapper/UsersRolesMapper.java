package com.blade.manager.system.modules.permission.mapper;

import com.blade.manager.system.common.persistence.BaseMapper;
import com.blade.manager.system.modules.permission.entity.UsersRoles;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author blade
 * @since 2019-09-17
 */
public interface UsersRolesMapper extends BaseMapper<UsersRoles> {

    /**
     * 根据用户ID删除角色
     *
     * @param userId 用户ID
     */
    void deleteByUserId(@Param("userId") Long userId);
}
