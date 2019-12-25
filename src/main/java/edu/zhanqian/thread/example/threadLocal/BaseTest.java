package edu.zhanqian.thread.example.threadLocal;

/**
 * @Date 2019/12/10 14:39
 * @Created by zhanqian
 * @Description 线程间的数据隔离  ThreadLocal为每一个线程都提供了变量的副本，使得每个线程在某一时间访问到的并不是同一个对象
 */
public class BaseTest {

    ThreadLocal<Long> longLocal = new ThreadLocal<>();
    ThreadLocal<String> stringLocal = new ThreadLocal<>();

    public void set() {
        longLocal.set(Thread.currentThread().getId());
        stringLocal.set(Thread.currentThread().getName());
    }

    public long getLong() {
        return longLocal.get();
    }

    public String getString() {
        return stringLocal.get();
    }

    public static void main(String[] args) throws InterruptedException {
        final BaseTest test = new BaseTest();

        test.set();
        System.out.println("main:" + test.getLong());
        System.out.println("main:" + test.getString());

        Thread thread1 = new Thread() {
            public void run() {
                test.set();
                System.out.println("thread1:" + test.getLong());
                System.out.println("thread1:" + test.getString());
            };
        };
        thread1.start();
        thread1.join();

        System.out.println("main:" + test.getLong());
        System.out.println("main:" + test.getString());
    }

}
