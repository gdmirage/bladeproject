package com.blade.manager.system.permission.service;

import com.blade.core.page.PageInfo;
import com.blade.core.service.IBaseService;
import com.blade.manager.system.permission.entity.Dept;
import com.blade.manager.system.permission.model.dept.DeptListSearchDTO;
import com.blade.manager.system.permission.model.dept.DeptListTreeVO;
import com.blade.manager.system.permission.model.dept.DeptTreeVO;
import com.blade.manager.system.permission.model.dept.DeptVO;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Blade
 * @since 2019-12-20 16:08:17
 */
public interface IDeptService extends IBaseService<Dept> {

    /**
     * 获取部门树
     *
     * @return {@link List<DeptTreeVO>}
     */
    List<DeptTreeVO> getDeptTree();

    /**
     * 获取部门列表
     *
     * @param searchDTO {@link DeptListSearchDTO}
     * @return {@link PageInfo<DeptListTreeVO>}
     */
    PageInfo<DeptListTreeVO> getDeptListTree(DeptListSearchDTO searchDTO);

    /**
     * 根据角色ID查询部门
     *
     * @param roleId role id
     * @return {@link List< DeptVO >}
     */
    List<DeptVO> getDeptsByRoleId(Long roleId);
}