package com.blade.manager.system.permission.service.impl;

import com.blade.manager.system.permission.service.IDeptService;
import org.springframework.stereotype.Service;
import com.blade.manager.system.permission.entity.Dept;
import com.blade.manager.system.permission.mapper.DeptMapper;
import com.blade.core.service.impl.BaseServiceImpl;
/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Blade
 * @since 2019-12-20 16:08:17
 */
@Service("deptService")
public class DeptServiceImpl extends BaseServiceImpl<DeptMapper, Dept> implements IDeptService {

}