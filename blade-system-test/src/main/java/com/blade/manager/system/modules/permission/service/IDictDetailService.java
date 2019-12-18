package com.blade.manager.system.modules.permission.service;

import com.blade.core.service.IBaseService;
import com.blade.manager.system.modules.permission.entity.DictDetail;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author blade
 * @since 2019-10-06
 */
public interface IDictDetailService extends IBaseService<DictDetail> {
    List<DictDetail> selectDictDetailByDictName(String name);
}
