package edu.zhanqian.thread.model.runnable;

/**
 * @Date 2019/12/2 11:37
 * @Created by zhanqian
 * @Description TODO
 */
public class MyThread extends Thread {

    private Thread t1;
    private Thread t2;

    public void setThread(Thread t1, Thread t2) {
        this.t1 = t1;
        this.t2 = t2;
    }

    @Override
    public void run() {
        while (t1.getState() != State.TERMINATED || t2.getState() != State.TERMINATED) {
            System.out.println("t1 state: " + t1.getState());
            System.out.println("t2 state: " + t2.getState());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("t1 state: " + t1.getState());
        System.out.println("t2 state: " + t2.getState());
    }
}
