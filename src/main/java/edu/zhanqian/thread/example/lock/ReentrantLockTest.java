package edu.zhanqian.thread.example.lock;

/**
 * @Date 2019/12/3 17:26
 * @Created by zhanqian
 * @Description ReentrantLock 锁绑定多个条件Condition
 */
public class ReentrantLockTest {
    /**
     *  * 锁绑定多个条件Condition
     *  * 题目：多线程之间按顺序执行，实现A->B->C三个线程启动，要求如下：
     *  *      A打印5次，B打印10次，C打印15次，
     *  *      紧接着
     *  *      A打印5次，B打印10次，C打印15次，
     *  *      .
     *  *      .
     *  *      .
     *  *      循环执行10轮
     *  
     */

    public static void main(String[] args) {
        PrintResource shareResource = new PrintResource();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shareResource.print5();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shareResource.print10();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shareResource.print15();
            }
        }, "C").start();
    }
}
