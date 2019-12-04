package edu.zhanqian.threadPool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Date 2019/12/4 13:31
 * @Created by zhanqian
 * @Description TODO
 */
public class ScheduledThreadPool {

    public static void main(String[] args) {
        //没有timeout的断开时长
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(4);

//        for (int i = 0; i < 10; i++) {
//            scheduledThreadPool.schedule(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println(Thread.currentThread().getName() + "\t延迟10秒执行！");
//                }
//            }, 10, TimeUnit.SECONDS);
//        }

        for (int i = 0; i < 10; i++) {
            scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "\t延迟1秒后每3秒执行一次");
                }
            }, 1, 3, TimeUnit.SECONDS);
        }
    }

}
