package com.blade.manager.system.modules.permission.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.blade.manager.system.modules.permission.entity.Job;
import com.blade.manager.system.modules.permission.model.job.JobListVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author blade
 * @since 2019-10-03
 */
public interface IJobService extends IService<Job> {
    PageInfo<JobListVO> page();
}
