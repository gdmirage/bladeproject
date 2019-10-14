package com.blade.manager.system.modules.permission.controller;


import com.blade.manager.system.common.BaseController;
import com.blade.manager.system.common.ResponseResult;
import com.blade.manager.system.modules.permission.model.job.JobListVO;
import com.blade.manager.system.modules.permission.model.job.JobPageSearchDTO;
import com.blade.manager.system.modules.permission.service.IJobService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author blade
 * @since 2019-10-03
 */
@RestController
@RequestMapping("/job")
public class JobController extends BaseController {

    private IJobService jobService;

    @Autowired
    public JobController(IJobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping("/page")
    public ResponseResult<PageInfo<JobListVO>> page(@RequestBody JobPageSearchDTO jobPageSearchDTO) {
        return ResponseResult.ok(200, "请求成功", jobService.page(jobPageSearchDTO));
    }
}
