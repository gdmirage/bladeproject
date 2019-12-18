package com.blade.manager.system.modules.permission.mapper;

import com.blade.core.persistence.mapper.BaseMapper;
import com.blade.manager.system.modules.permission.entity.Menu;
import com.blade.manager.system.modules.permission.model.menu.MenuListSearchDTO;
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
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 根据roleIds 获取菜单
     *
     * @param roleIds 角色id
     * @return {@link List<Menu>}
     */
    List<Menu> selectByRoleIds(@Param("roleIds") List<Long> roleIds);

    /**
     * 获取菜单
     *
     * @param menuListSearchDTO {@link MenuListSearchDTO}
     * @return {@link List<Menu>}
     */
    List<Menu> selectMenuList(@Param("searchDTO") MenuListSearchDTO menuListSearchDTO);
}
