package com.blade.manager.system.modules.permission.mapper;

import com.blade.core.persistence.mapper.BaseMapper;
import com.blade.manager.system.modules.permission.entity.RolesDepts;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author blade
 * @since 2019-11-14
 */
public interface RolesDeptsMapper extends BaseMapper<RolesDepts> {

    /**
     * 根据角色id 删除
     *
     * @param roleId 角色Id
     */
    void deleteByRoleId(@Param("roleId") Long roleId);
}
