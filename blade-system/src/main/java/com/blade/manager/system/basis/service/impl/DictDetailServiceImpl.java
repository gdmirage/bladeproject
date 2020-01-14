package com.blade.manager.system.basis.service.impl;

import com.blade.core.service.impl.BaseServiceImpl;
import com.blade.manager.system.basis.entity.DictDetail;
import com.blade.manager.system.basis.mapper.DictDetailMapper;
import com.blade.manager.system.basis.service.IDictDetailService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Blade
 * @since 2019-12-20 15:53:36
 */
@Service("dictDetailService")
public class DictDetailServiceImpl extends BaseServiceImpl<DictDetailMapper, DictDetail> implements IDictDetailService {

    @Override
    public List<DictDetail> getByDictName(String dictName) {
        return super.baseMapper.getByDictName(dictName);
    }
}