package com.blade.manager.system.basis.service;

import com.blade.core.service.IBaseService;
import com.blade.manager.system.basis.entity.DictDetail;

import java.util.List;

/**
 * <p>
 * 字典详情服务类
 * </p>
 *
 * @author Blade
 * @since 2019-12-20 15:53:36
 */
public interface IDictDetailService extends IBaseService<DictDetail> {

    /**
     * 根据字典名称获取对应详情
     *
     * @param dictName dict name
     * @return {@link List<DictDetail>}
     */
    List<DictDetail> getByDictName(String dictName);
}