package com.blade.manager.system.modules.sys;

import com.blade.manager.system.modules.sys.entity.SysUser;
import com.blade.manager.system.modules.sys.service.ISysUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * TODO:
 *
 * @author Blade
 * @date 2019/2/13 11:07
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestSysUserService {
    static Logger logger = LogManager.getLogger(TestSysUserService.class);
    @Autowired
    private ISysUserService sysUserService;

    @Test
    public void testSelectById() {
        SysUser user = sysUserService.selectById(1);
        logger.info("==============");
        System.out.println(user);
    }
}
