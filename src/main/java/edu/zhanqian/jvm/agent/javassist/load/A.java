package edu.zhanqian.jvm.agent.javassist.load;

/**
 * @Date 2020/4/9 13:18
 * @Created by zhanqian
 * @Description TODO
 */
public class A {

    int age;

    public void a() {
        if (age > 10) {
            System.out.println("111");
        } else {
            System.out.println("nn");
        }
    }
}
