package com.blade.manager.system.permission.service;

import com.blade.manager.system.permission.entity.Dept;
import com.blade.core.service.IBaseService;
import com.blade.manager.system.permission.model.dept.DeptTreeVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Blade
 * @since 2019-12-20 16:08:17
 */
public interface IDeptService extends IBaseService<Dept> {

    /**
     * 获取部门树
     * @return {@link List<DeptTreeVO>}
     */
    List<DeptTreeVO> getDeptTree();
}