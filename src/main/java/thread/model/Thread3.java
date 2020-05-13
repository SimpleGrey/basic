package thread.model;

/**
 * @Date 2019/12/2 10:33
 * @Created by zhanqian
 * @Description TODO
 */
public class Thread3 extends Thread {
    @Override
    public void run() {
        System.out.println("进入t3线程，准备睡眠");

        //本来是想让t3线程做加法运算的，奈何电脑算太快了，所以改为睡眠。因为睡眠不释放锁，所以效果一样。
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("t3线程结束");
    }
}