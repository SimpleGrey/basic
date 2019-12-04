package edu.zhanqian.thread.example.state;

import edu.zhanqian.thread.model.*;
import edu.zhanqian.thread.model.runnable.MyThread;
import edu.zhanqian.thread.model.runnable.RThread1;

/**
 * @Date 2019/12/2 9:43
 * @Created by zhanqian
 * @Description TODO
 */
public class TestThreadState {


    public static void main(String[] args) throws InterruptedException {
        //当线程调用sleep()方法的时候进入了定时等待状态。
//        TestSleepState();

        //当其他线程调用了join()方法时进入等待状态。
//        TestJoinState();

        //当线程遇到I/O的时候还是运行状态
//        TestIOState();

        //当遇到synchronize blocked状态
        TestSynchronizedState();

        System.out.println("main方法 结束");

    }

    private static void TestSynchronizedState() {
        RThread1 rt1 = new RThread1();
        Thread t1 = new Thread(rt1);
        Thread t2 = new Thread(rt1);

        MyThread myThread = new MyThread();
        myThread.setThread(t1, t2);

        myThread.start();
        t1.start();
        t2.start();
    }

    private static void TestIOState() {
        Thread1 t1 = new Thread1();
        Thread5 t5 = new Thread5();
        t1.setThread5(t5);

        t5.start();
        t1.start();
    }

    private static void TestJoinState() throws InterruptedException {
        Thread3 t3 = new Thread3();
        Thread4 t4 = new Thread4();
        Thread1 t1 = new Thread1();

        t1.setThread(t3, t4);
        t4.setThread3(t3);

        t1.start();
        t4.start();
    }

    public static void TestSleepState() {
        Thread2 t2 = new Thread2();
        Thread1 t1 = new Thread1();

        t1.setThread2(t2);

        t2.start();
        t1.start();
    }

}
