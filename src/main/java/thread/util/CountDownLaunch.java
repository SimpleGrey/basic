package thread.util;

import tool.OkHttpUtil;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhanqian
 * @Date 2020/12/9 10:41
 * @Description TODO
 */
public class CountDownLaunch {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(50);
        for (int i = 0; i < 50; i++) {
            new Thread(new readNum(i, countDownLatch)).start();
        }
        countDownLatch.await();
        System.out.println("线程执行结束。。。。");
    }

    static class readNum implements Runnable {
        private int id;
        private CountDownLatch latch;

        public readNum(int id, CountDownLatch latch) {
            this.id = id;
            this.latch = latch;
        }

        @Override
        public void run() {
            synchronized (this) {
                System.out.println("id:" + id);
                latch.countDown();
                try {
                    Long s1 = System.currentTimeMillis();
                    OkHttpUtil.post("https://testgusteau.xinhuaapp.com/server/component-server/oss/auth/net-streaming/image/upload", "66ecf35b-16c3-4674-9f66-b5ce90383bb7");
                    Long s2 = System.currentTimeMillis();
                    System.out.println(s2 - s1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("线程组任务" + id + "结束，其他任务继续");
            }
        }
    }

}
