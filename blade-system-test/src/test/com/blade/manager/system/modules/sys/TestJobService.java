package com.blade.manager.system.modules.sys;

import com.blade.manager.system.modules.permission.service.IJobService;
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
        System.out.println("---");
    }
}