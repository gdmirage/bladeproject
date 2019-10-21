package com.blade.manager.system.modules.permission.service;

import com.blade.manager.system.modules.permission.entity.Dept;
import com.baomidou.mybatisplus.extension.service.IService;
import com.blade.manager.system.modules.permission.model.dept.DeptTreeVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author blade
 * @since 2019-10-14
 */
public interface IDeptService extends IService<Dept> {

    List<DeptTreeVO> findDeptTree();
}
