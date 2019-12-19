package com.blade.manager.system.basis.service.impl;

import com.blade.manager.system.basis.service.IDictService;
import org.springframework.stereotype.Service;
import com.blade.manager.system.basis.entity.Dict;
import com.blade.manager.system.basis.mapper.DictMapper;
import com.blade.core.service.impl.BaseServiceImpl;
/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Blade
 * @since 2019-12-19 16:00:20
 */
@Service("dictService")
public class DictServiceImpl extends BaseServiceImpl<DictMapper, Dict> implements IDictService {

}