package com.blade.manager.system.modules.permission.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blade.manager.system.modules.permission.entity.Job;
import com.blade.manager.system.modules.permission.mapper.JobMapper;
import com.blade.manager.system.modules.permission.service.IJobService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author blade
 * @since 2019-10-03
 */
@Service("jobService")
public class JobServiceImpl extends ServiceImpl<JobMapper, Job> implements IJobService {

    public Page<Job> page() {
        PageHelper.startPage(1, 10);
        List<Job> jobList =  baseMapper.selectPage();
        return (Page) jobList;
    }
}
