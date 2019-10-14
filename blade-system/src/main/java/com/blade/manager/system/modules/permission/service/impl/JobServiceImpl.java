package com.blade.manager.system.modules.permission.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blade.manager.system.modules.permission.entity.Dept;
import com.blade.manager.system.modules.permission.entity.Job;
import com.blade.manager.system.modules.permission.mapper.JobMapper;
import com.blade.manager.system.modules.permission.model.job.JobListVO;
import com.blade.manager.system.modules.permission.service.IDeptService;
import com.blade.manager.system.modules.permission.service.IJobService;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

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

    @Autowired
    private IDeptService deptService;

    @Override
    public PageInfo<JobListVO> page() {
        PageInfo<JobListVO> pageInfo = PageHelper.startPage(1, 10)
                .doSelectPageInfo(() -> {
                            baseMapper.selectPage();
                        }
                );

        if (!CollectionUtils.isEmpty(pageInfo.getList())) {
            List<JobListVO> list = pageInfo.getList();
            list.forEach(jobListVO -> {
                Dept dept = deptService.getById(jobListVO.getDeptId());
                jobListVO.setDeptSuperiorName(dept.getName());
            });
        }

        return pageInfo;
    }
}
