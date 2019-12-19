package com.blade.manager.system.modules.permission.mapper;

import com.blade.core.persistence.mapper.BaseMapper;
import com.blade.manager.system.modules.permission.entity.RolesPermissions;
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
public interface RolesPermissionsMapper extends BaseMapper<RolesPermissions> {

    /**
     * 获取用户的权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    List<String> selectUserPermissionsByUserId(@Param("userId") Long userId);

    /**
     * 根据角色Id删除
     *
     * @param roleId 角色Id
     */
    void deleteByRoleId(@Param("roleId") Long roleId);
}