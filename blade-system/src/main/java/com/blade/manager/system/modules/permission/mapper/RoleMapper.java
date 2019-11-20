package com.blade.manager.system.modules.permission.mapper;

import com.blade.core.persistence.mapper.BaseMapper;
import com.blade.manager.system.modules.permission.entity.Role;
import com.blade.manager.system.modules.permission.model.role.RoleListVO;
import com.blade.manager.system.modules.permission.model.role.RolePageSearchDTO;
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
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据用户ID获取角色信息
     *
     * @param userId 用户ID
     * @return {@link List<Role>}
     */
    List<Role> selectRolesByUserId(@Param("userId") long userId);

    /**
     * 角色分页查询
     *
     * @param searchDTO {@link RolePageSearchDTO}
     * @return {@link List<RoleListVO>}
     */
    List<RoleListVO> selectPage(@Param("searchDTO") RolePageSearchDTO searchDTO);
}
