package edu.zhanqian.jvm.agent.javassist;

import javassist.*;

import java.io.IOException;

public class JavassistTest {
    public static void main(String[] args) throws NotFoundException, CannotCompileException, IllegalAccessException, InstantiationException, IOException {
        Base2 b = new Base2();

        ClassPool cp = ClassPool.getDefault();
        CtClass cc = cp.get("edu.zhanqian.jvm.agent.javassist.Base2");
        CtMethod m = cc.getDeclaredMethod("process");
        m.insertBefore("{ System.out.println(\"start\"); }");
        m.insertAfter("{ System.out.println(\"end\"); }");
        Class c = cc.toClass();
        Base2 h = (Base2) c.newInstance();
        h.process();
    }
}