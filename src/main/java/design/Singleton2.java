package design;

import org.jets3t.apps.synchronize.Synchronize;

/**
 * @author zhanqian
 * @Date 2020/12/4 14:35
 * @Description
 */
public class Singleton2 {

    private static Singleton2 instance;

    private Singleton2() {}

    /**
     * 懒汉-线程不安全
     * @return
     */
//    public static Singleton2 getInstance() {
//        if (instance == null) {
//            instance = new Singleton2();
//        }
//        return instance;
//    }

    /**
     * 懒汉-线程安全
     * @return
     */
    public static synchronized Singleton2 getInstance() {
        if (instance == null) {
            instance = new Singleton2();
        }
        return instance;
    }



}
