package com.blade.manager.system;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
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
    private RedissonClient redisson;

    @Test
    public void testRedisson() {
        redisson.getBucket("blade").set("haha");

        RBucket<String> rBucket = redisson.getBucket("blade");
        System.out.println(rBucket.get());
    }
}
