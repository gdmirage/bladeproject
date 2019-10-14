package com.blade.manager.system.modules.permission.service.impl;

import com.blade.manager.system.modules.permission.entity.Dept;
import com.blade.manager.system.modules.permission.mapper.DeptMapper;
import com.blade.manager.system.modules.permission.service.IDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author blade
 * @since 2019-10-14
 */
@Service("deptService")
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {

}
