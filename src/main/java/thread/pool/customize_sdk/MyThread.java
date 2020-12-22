package thread.pool.customize_sdk;

/**
 * @Date 2019/12/4 19:51
 * @Created by zhanqian
 * @Description TODO
 */
public class MyThread implements Runnable {

    private int id;

    public MyThread(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "\t 正在被执行" + id);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
