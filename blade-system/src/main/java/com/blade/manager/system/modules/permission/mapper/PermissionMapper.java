package com.blade.manager.system.modules.permission.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
}
