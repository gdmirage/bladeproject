package com.blade.manager.system.modules.permission.mapper;

import com.blade.core.persistence.mapper.BaseMapper;
import com.blade.manager.system.modules.permission.entity.Dept;
import com.blade.manager.system.modules.permission.model.dept.DeptTreeVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author blade
 * @since 2019-10-14
 */
public interface DeptMapper extends BaseMapper<Dept> {

    /**
     * 根据父节点获取部门
     *
     * @param pid 父id
     * @return {@link List<DeptTreeVO>}
     */
    List<DeptTreeVO> selectDeptByPid(@Param("pid") long pid);

    /**
     * 根据角色获取部门信息
     *
     * @param roleIds 角色ids
     * @return {@link List<Dept>}
     */
    List<Dept> selectByRoleIds(@Param("roleIds") List<Long> roleIds);
}
