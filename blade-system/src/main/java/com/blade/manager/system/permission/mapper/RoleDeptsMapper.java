package com.blade.manager.system.permission.mapper;

import com.blade.core.persistence.mapper.BaseMapper;
import com.blade.manager.system.permission.entity.RoleDepts;
import com.blade.manager.system.permission.model.dept.DeptVO;
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
public interface RoleDeptsMapper extends BaseMapper<RoleDepts> {
    /**
     * 根据角色ID删除
     *
     * @param roleId role id
     * @return delete row num
     */
    int deleteByRoleId(@Param("roleId") Long roleId);
}