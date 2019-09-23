package com.blade.manager.system.modules.permission.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blade.manager.system.modules.permission.entity.Menu;
import com.blade.manager.system.modules.permission.model.menu.MenuDTO;
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

    List<MenuDTO> selectByRoleIds(@Param("roleIds") List<Long> roleIds);
}
