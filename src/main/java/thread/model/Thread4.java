package thread.model;

/**
 * @Date 2019/12/2 10:33
 * @Created by zhanqian
 * @Description TODO
 */
public class Thread4 extends Thread {
    private Thread3 t3;

    public void setThread3(Thread3 t3) {
        this.t3 = t3;
    }

    //当进入t2线程以后马上启动t3线程并调用join()方法。
    @Override
    public void run() {
        System.out.println("进入t2线程，t3准备加入(调用join()方法)");
        t3.start();
        try {

            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("t2执行结束");
    }
}