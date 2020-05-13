package thread.model;

import java.util.Scanner;

/**
 * @Date 2019/12/2 10:44
 * @Created by zhanqian
 * @Description TODO
 */
public class Thread5 extends Thread {

    @Override
    public void run() {
        System.out.println("进入t5线程");
        System.out.println("请输入数据: ");
        Scanner scanner = new Scanner(System.in);
        String read = scanner.nextLine();
        System.out.println("您输入的数据为：" + read);

        System.out.println("t5线程结束");
    }
}
