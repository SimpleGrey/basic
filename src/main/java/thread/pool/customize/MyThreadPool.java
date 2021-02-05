package thread.pool.customize;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author zhanqian
 * @Date 2020/12/22 11:33
 * @Description 简化线程池 参考https://time.geekbang.org/column/article/90771
 */
public class MyThreadPool {

    //利用阻塞队列实现生产者-消费者模式
    BlockingQueue<Runnable> workQueue;
    //保存内部工作线程
    List<WorkerThread> list = new ArrayList<>();

    MyThreadPool(int poolSize, BlockingQueue<Runnable> workQueue) {
        //传入队列
        this.workQueue = workQueue;

        //创建工作线程
        for (int i = 0; i < poolSize; i++) {
            WorkerThread workerThread = new WorkerThread();
            workerThread.start();
            list.add(workerThread);
        }
    }

    //提交任务
    public void execute(Runnable command) throws InterruptedException {
        workQueue.put(command);
    }

    //工作线程负责消费任务
    class WorkerThread extends Thread {
        @Override
        public void run() {
            //循环取任务并执行
            while (true) {
                try {
                    Runnable task = workQueue.take();
                    task.run();
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class AAA {

        public void bbb() {

        }
    }

    public static void main(String[] args) throws InterruptedException {
        //使用示例
        BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<>(2);
        MyThreadPool pool = new MyThreadPool(2, workQueue);
        pool.execute(() -> System.out.println("hello1"));
        pool.execute(() -> System.out.println("hello2"));
        pool.execute(() -> System.out.println("hello3"));
        pool.execute(() -> System.out.println("hello4"));
    }
}
