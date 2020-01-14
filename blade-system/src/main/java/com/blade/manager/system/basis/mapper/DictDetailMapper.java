package com.blade.manager.system.basis.mapper;

import com.blade.core.persistence.mapper.BaseMapper;
import com.blade.manager.system.basis.entity.DictDetail;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Blade
 * @since 2019-12-20 15:53:36
 */
public interface DictDetailMapper extends BaseMapper<DictDetail> {

    /**
     * 根据字典名称获取对应详情
     *
     * @param dictName dict name
     * @return {@link List<DictDetail>}
     */
    List<DictDetail> getByDictName(String dictName);
}