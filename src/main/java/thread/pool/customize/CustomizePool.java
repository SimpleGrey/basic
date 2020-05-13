package thread.pool.customize;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Date 2019/12/4 19:49
 * @Created by zhanqian
 * @Description 自定义线程池，可以用ThreadPoolExecutor类创建，它有多个构造方法来创建线程池。
 */
public class CustomizePool {

    public static void main(String[] args) {
        // 创建数组型缓冲等待队列
        BlockingQueue<Runnable> bq = new ArrayBlockingQueue<Runnable>(10);

        // ThreadPoolExecutor:创建自定义线程池，池中保存的线程数为3，允许最大的线程数为6
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(3, 6, 50, TimeUnit.MILLISECONDS, bq);

        Runnable r1 = new MyThread(1);
        Runnable r2 = new MyThread(2);
        Runnable r3 = new MyThread(3);
        Runnable r4 = new MyThread(4);

        tpe.execute(r1);
        tpe.execute(r2);
        tpe.execute(r3);
        tpe.execute(r4);

        tpe.shutdown();
    }

}
