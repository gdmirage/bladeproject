package com.blade.manager.system.permission.mapper;

import com.blade.core.persistence.mapper.BaseMapper;
import com.blade.manager.system.permission.entity.Menu;
import com.blade.manager.system.permission.model.menu.MenuListSearchDTO;
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

    /**
     * 获取全部菜单
     *
     * @param menuListSearchDTO {@link MenuListSearchDTO}
     * @return {@link List<Menu>}
     */
    List<Menu> selectList(@Param("searchDTO") MenuListSearchDTO menuListSearchDTO);
}