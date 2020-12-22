package design;

/**
 * @author zhanqian
 * @Date 2020/12/4 14:35
 * @Description 最简单的  饿汉式
 */
public class Singleton {

    private static Singleton instance = new Singleton();

    private Singleton() {}

    public static Singleton getInstance() {
        return instance;
    }
}
