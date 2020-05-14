package com.blade.manager.system;

import com.blade.manager.system.permission.entity.User;
import com.blade.starter.redis.RedisUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * TODO:
 *
 * @author Blade
 * @date 2020/4/28 16:59
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ManagerSystemApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseTest {

    @Autowired
    private RedisUtils redisUtils;

    @Test
    public void testRedisson() {
        User user = new User();
        user.setAvatar("aaa");
        user.setLoginName("abbb");
        redisUtils.save("user", "blade");

        System.out.println("1111111111111=====" + redisUtils.get("user"));

        redisUtils.delete("user");

        System.out.println("2222222222222=====" + redisUtils.get("user"));
    }
}
