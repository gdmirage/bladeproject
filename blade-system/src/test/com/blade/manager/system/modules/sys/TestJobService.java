package com.blade.manager.system.modules.sys;

import com.alibaba.fastjson.JSON;
import com.blade.manager.system.modules.permission.entity.Job;
import com.blade.manager.system.modules.permission.model.job.JobListVO;
import com.blade.manager.system.modules.permission.service.IJobService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * TODO:
 *
 * @author Blade
 * @date 2019/10/3 21:43
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestJobService {
    @Autowired
    private IJobService jobService;

    @Test
    public void testSelectPage(){
        PageInfo<JobListVO> page = jobService.page();
        System.out.println(JSON.toJSONString(page));
    }
}
