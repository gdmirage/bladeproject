package com.blade.manager.system.modules.permission.service.impl;

import com.blade.manager.system.modules.permission.entity.DictDetail;
import com.blade.manager.system.modules.permission.mapper.DictDetailMapper;
import com.blade.manager.system.modules.permission.service.IDictDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author blade
 * @since 2019-10-06
 */
@Service("dictDetailService")
public class DictDetailServiceImpl extends ServiceImpl<DictDetailMapper, DictDetail> implements IDictDetailService {

    public List<DictDetail> selectDictDetailByDictName(String name) {
        return super.baseMapper.selectDictDetailByDictName(name);
    }
}
