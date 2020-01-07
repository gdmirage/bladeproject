package com.blade.manager.system.permission.service.impl;

import com.blade.manager.system.permission.service.IJobService;
import org.springframework.stereotype.Service;
import com.blade.manager.system.permission.entity.Job;
import com.blade.manager.system.permission.mapper.JobMapper;
import com.blade.core.service.impl.BaseServiceImpl;
/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Blade
 * @since 2019-12-20 16:08:17
 */
@Service("jobService")
public class JobServiceImpl extends BaseServiceImpl<JobMapper, Job> implements IJobService {



}