package com.blade.manager.system.modules.permission.mapper;

import com.blade.manager.system.common.persistence.BaseMapper;
import com.blade.manager.system.modules.permission.entity.RolesMenus;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author blade
 * @since 2019-09-17
 */
public interface RolesMenusMapper extends BaseMapper<RolesMenus> {
    /**
     * 根据角色Id删除
     *
     * @param roleId 角色Id
     */
    void deleteByRoleId(@Param("roleId") Long roleId);
}
