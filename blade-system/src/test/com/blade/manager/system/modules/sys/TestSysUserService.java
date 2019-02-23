package com.blade.manager.system.modules.sys;

import com.blade.manager.system.common.persistence.entity.SysUser;
import com.blade.manager.system.modules.sys.service.ISysUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
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
    public void testInset() {
        SysUser user = new SysUser();
        user.setLoginName("chenjiangxin");
        user.setUserName("陈江新");
        int i = sysUserService.insert(user);
        logger.info("insert num is : " + i);
    }

    @Test
    public void testUpdate() {
        SysUser user = sysUserService.selectByPK(1);
        user.setUserName("Blade2");
        int i = sysUserService.update(user);

        Assert.assertEquals(1, i);
    }

    @Test
    public void testDelete(){
        int i = sysUserService.delete(1);
        logger.info("delete num : " + i);
        Assert.assertEquals(1, i);
    }

    @Test
    public void testSelectById() {
        SysUser user = sysUserService.selectByPK(1);
        logger.info("==============");
        logger.info("user.loginName = {} , user.userName = {}", user.getLoginName(), user.getUserName());
    }

    @Test
    public void testTransaction() {
        try {
            sysUserService.testTransaction();
        }catch (Exception e) {
            logger.error("aaa", e);
        }
    }

}
