package thread.pool.customize;

import org.spark_project.jetty.util.BlockingArrayQueue;

import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * @author zhanqian
 * @Date 2021/2/5 9:53
 * @Description TODO
 */
public class MyThreadPoolCopy {

    BlockingQueue<Runnable> blockingQueue;
    List<MyThreadCopy> myThreadCopyList;

    public MyThreadPoolCopy(int poolSize, int queueSize) {
        blockingQueue = new BlockingArrayQueue(queueSize);

    }

    class MyThreadCopy extends Thread {

        @Override
        public void run() {
            try {
                Runnable runnable = blockingQueue.take();
                runnable.run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
