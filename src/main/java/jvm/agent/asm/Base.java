package jvm.agent.asm;

public class Base {
    public void process(){
        System.out.println("process");
    }
    public void other(){
        System.out.println("process");
    }

    public static void main(String[] args) {
        new Base().process();
    }
}
