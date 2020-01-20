package com.blade.manager.system.permission.mapper;

import com.blade.core.persistence.mapper.BaseMapper;
import com.blade.manager.system.permission.entity.Role;
import com.blade.manager.system.permission.model.role.RoleListVO;
import com.blade.manager.system.permission.model.role.RolePageSearchDTO;
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
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 分页查询
     *
     * @param searchDTO {@link RolePageSearchDTO}
     * @return {@link List<RoleListVO>}
     */
    List<RoleListVO> selectPage(@Param("searchDTO") RolePageSearchDTO searchDTO);
}