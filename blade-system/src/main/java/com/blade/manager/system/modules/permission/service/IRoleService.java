package com.blade.manager.system.modules.permission.service;

import com.blade.manager.system.modules.permission.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author blade
 * @since 2019-09-17
 */
public interface IRoleService extends IService<Role> {

    List<Role> getRolesByUserId(long userId);
}
