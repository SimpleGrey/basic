package thread.model;

/**
 * @Date 2019/12/2 9:47
 * @Created by zhanqian
 * @Description TODO
 */
public class Thread2 extends Thread {


    @Override
    public void run() {
        System.out.println("进入t2线程，马上进入睡眠");
        try {
            //睡眠5秒钟。
            suspend();
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("t2睡眠结束");
    }
}
