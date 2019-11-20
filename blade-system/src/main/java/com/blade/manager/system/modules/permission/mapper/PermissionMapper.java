package com.blade.manager.system.modules.permission.mapper;

import com.blade.core.persistence.mapper.BaseMapper;
import com.blade.manager.system.modules.permission.entity.Permission;
import com.blade.manager.system.modules.permission.model.permission.PermissionSearchDTO;
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
public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * 查询所有的权限
     *
     * @return List<Permission>
     */
    List<Permission> selectPermissionList(@Param("searchDTO") PermissionSearchDTO permissionSearchDTO);

    /**
     * 查询子节点
     *
     * @param pid 父节点id
     * @return List<Permission>
     */
    List<Permission> selectPermissionListByPid(@Param("pid") Integer pid);

    /**
     * 根据角色Id 获取权限内容
     * @param roleIds 角色ids
     * @return {@link List<Permission>}
     */
    List<Permission> selectByRoleIds(@Param("roleIds") List<Long> roleIds);
}
