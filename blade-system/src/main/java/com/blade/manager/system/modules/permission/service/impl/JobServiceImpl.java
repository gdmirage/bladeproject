package com.blade.manager.system.modules.permission.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blade.manager.system.modules.permission.entity.Job;
import com.blade.manager.system.modules.permission.mapper.JobMapper;
import com.blade.manager.system.modules.permission.service.IJobService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author blade
 * @since 2019-10-03
 */
@Service("jobService")
public class JobServiceImpl extends ServiceImpl<JobMapper, Job> implements IJobService {

    @Override
    public PageInfo<Job> page() {
        return PageHelper.startPage(1, 10)
                .doSelectPageInfo(() -> baseMapper.selectPage());
    }
}
