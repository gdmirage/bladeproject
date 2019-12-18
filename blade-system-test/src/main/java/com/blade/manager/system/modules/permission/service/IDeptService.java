package com.blade.manager.system.modules.permission.service;

import com.blade.core.service.IBaseService;
import com.blade.manager.system.modules.permission.entity.Dept;
import com.blade.manager.system.modules.permission.model.dept.DeptTreeVO;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author blade
 * @since 2019-10-14
 */
public interface IDeptService extends IBaseService<Dept> {

    /**
     * 根据部门树
     *
     * @return {@link List<DeptTreeVO>}
     */
    List<DeptTreeVO> findDeptTree();

    /**
     * 根据角色获取部门信息
     *
     * @param roleId 角色id
     * @return {@link List<Dept>}
     */
    List<Dept> selectByRoleId(Long roleId);
}
