package thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Date 2019/12/4 13:21
 * @Created by zhanqian
 * @Description TODO
 */
public class CacheThreadPool {

    public static void main(String[] args) {
        //timeout默认60秒断开
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "\t正在执行！");
                }
            });
        }
    }
}
