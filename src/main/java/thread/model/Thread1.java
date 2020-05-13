package thread.model;

/**
 * @Date 2019/12/2 9:47
 * @Created by zhanqian
 * @Description TODO
 */
public class Thread1 extends Thread {

    private Thread2 t2;
    private Thread3 t3;
    private Thread4 t4;
    private Thread5 t5;

    public void setThread2(Thread2 t2) {
        this.t2 = t2;
    }

    public void setThread5(Thread5 t5) {
        this.t5 = t5;
    }

    public void setThread(Thread3 t3, Thread4 t4) {
        this.t4 = t4;
        this.t3 = t3;
    }

    @Override
    public void run() {
        System.out.println("进入t1线程");
        for (int i = 0; i < 6; i++) {
            try {
                System.out.println("t1 的状态： " + getState());
                if (t2 != null) {
                    System.out.println("t2 的状态： " + t2.getState());
                }
                if (t3 != null) {
                    System.out.println("t3 的状态： " + t3.getState());
                }
                if (t4 != null) {
                    System.out.println("t4 的状态： " + t4.getState());
                }
                if (t5 != null) {
                    System.out.println("t5 的状态： " + t5.getState());
                }
                System.out.println();

                //为了减少打印次数，所以t1每打印一次睡1秒
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
        }
        System.out.println("t1线程结束");
    }
}
