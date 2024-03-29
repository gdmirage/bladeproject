package com.blade.manager.system.permission.controller;

import com.blade.core.controller.BaseController;
import com.blade.core.page.PageInfo;
import com.blade.manager.system.permission.entity.Job;
import com.blade.manager.system.permission.model.job.JobPageSearchDTO;
import com.blade.manager.system.permission.model.job.JobListVO;
import com.blade.manager.system.permission.service.IJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Blade
 * @since 2019-12-20 16:08:17
 */
@RestController
@RequestMapping("/permission/job")
public class JobController extends BaseController {
    private static final long serialVersionUID = 4954630210003473679L;
    private IJobService jobService;

    @Autowired
    public JobController(IJobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping("/page")
    public PageInfo<JobListVO> page(@RequestBody JobPageSearchDTO jobPageSearchDTO) {
        return this.jobService.page(jobPageSearchDTO);
    }

    @PostMapping("/delete")
    public void delete(@RequestBody Integer id) {
        this.jobService.delete(id);
    }

    @GetMapping("/getById/{id}")
    public Job getById(@PathVariable Integer id) {
        return this.jobService.selectByPk(id);
    }

    @PostMapping("/add")
    public void add(@RequestBody Job job) {
        this.jobService.insert(job);
    }

    @PostMapping("/edit")
    public void update(@RequestBody Job job) {
        this.jobService.update(job);
    }
}