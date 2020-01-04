package com.blade.manager.system.permission.mapper;

import com.blade.core.persistence.mapper.BaseMapper;
import com.blade.manager.system.permission.entity.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Blade
 * @since 2019-12-20 16:08:17
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 根据角色ID获取menu
     *
     * @param roleIds 角色ID
     * @return menus
     */
    List<Menu> selectMenuByRoleIds(@Param("roleIds") List<Integer> roleIds);
}