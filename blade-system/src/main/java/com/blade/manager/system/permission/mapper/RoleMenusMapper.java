package com.blade.manager.system.permission.mapper;

import com.blade.core.persistence.mapper.BaseMapper;
import com.blade.manager.system.permission.entity.RoleMenus;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Blade
 * @since 2019-12-20 16:08:17
 */
public interface RoleMenusMapper extends BaseMapper<RoleMenus> {

    /**
     * 根据角色ID删除菜单
     *
     * @param roleId 角色ID
     * @return delete row num
     */
    int deleteByRoleId(@Param("roleId") Long roleId);
}