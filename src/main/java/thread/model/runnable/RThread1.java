package thread.model.runnable;

/**
 * @Date 2019/12/2 11:31
 * @Created by zhanqian
 * @Description TODO
 */
public class RThread1 implements Runnable {

    @Override
    public void run() {
        for (int j = 0; j < 10; j++) {
            System.out.println(Thread.currentThread().getName() + " - " + j);
        }
        try {
            System.out.println(Thread.currentThread().getName() + " 开始睡眠");
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName() + " 睡眠结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
