package com.blade.manager.system;

import com.blade.manager.system.permission.entity.User;
import com.blade.starter.redis.lock.RedisLock;
import com.blade.starter.redis.RedisUtils;
import com.blade.util.threadpool.ThreadPoolManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

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

    @Autowired
    private RedisLock redisLock;

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

    @Test
    public void testLock() throws IOException {
        String key = "blade";

        for (int i = 0; i < 1000; i++) {
            ThreadPoolManager.executeThread(() -> {
                boolean lock = false;
                try {
                    lock = redisLock.tryLock(key, 6000L);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + " 获取锁失败");
                }
                if (lock) {
                    System.out.println(Thread.currentThread().getName() + " 获取锁成功");
                    try {
                        TimeUnit.MILLISECONDS.sleep(50L);
                        System.out.println(Thread.currentThread().getName() + " 解锁成功");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    redisLock.unlock(key);
                } else {
                    System.out.println(Thread.currentThread().getName() + " 获取锁失败");
                }
            });
        }

        System.in.read();
    }
}
