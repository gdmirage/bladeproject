package com.blade.practice.zookeeper;

import com.blade.practice.zookeeper.lock.DistributedReentrantLock;
import com.blade.practice.zookeeper.scan.ScanAnnotation;
import org.apache.curator.framework.CuratorFramework;

/**
 * TODO:
 *
 * @author chenjiangxin
 * @date 2018/9/29 14:59
 */
public class TestZookeeper {

    public static void main(String[] args) throws Exception {

        ScanAnnotation.scan();

        lock();

    }

    private static void createNode() throws Exception {
        CuratorFramework curatorFramework = ZookeeperClientUtils.getInstance();
        curatorFramework.start();
        curatorFramework.create().creatingParentsIfNeeded().forPath("/blade1/bbb", "haha".getBytes("UTF-8"));
    }

    private static void lock() throws InterruptedException {
        DistributedReentrantLock lock = new DistributedReentrantLock("blade");

        lock.lock();
//        System.out.println(lock.tryLock());
        Thread.sleep(5000);
        System.out.println("another lock start");
        lock.lock();
        System.out.println("another lock end");
//        System.out.println(lock.tryLock());
        Thread.sleep(10000);
        lock.unlock();
        lock.unlock();
    }
}
