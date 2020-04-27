package edu.zhanqian.jvm.agent.javassist.load;

import javassist.*;
import org.springframework.util.NumberUtils;

import javax.sound.midi.Soundbank;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.SQLOutput;

/**
 * @Date 2020/4/9 9:28
 * @Created by zhanqian
 * @Description TODO
 */
public class JavassistTest {

    public static void main(String[] args) throws Exception {
        //ClassPool：ctClass对象的容器
        ClassPool pool = ClassPool.getDefault();
        //通过ClassPool深沉给一个public的新类 User.java
        CtClass ctClass = pool.makeClass("com.javassist.User");

        //添加属性，首先添加age属性：private Integer age;
        CtField ageField = new CtField(pool.getCtClass("java.lang.Integer"), "age", ctClass);
        ageField.setModifiers(Modifier.PRIVATE);
        ctClass.addField(ageField);

        //添加属性，其次添加phone属性：private String phone;
        CtField phoneField = new CtField(pool.getCtClass("java.lang.String"), "phone", ctClass);
        phoneField.setModifiers(Modifier.PRIVATE);
        ctClass.addField(phoneField);

        //添加属性，其次添加phone属性：private String phone;
        CtField aField = new CtField(CtClass.intType, "grade", ctClass);
        aField.setModifiers(Modifier.PRIVATE);
        ctClass.addField(aField);

        //为age和phone添加getXxx/setXxx方法
        ctClass.addMethod(CtNewMethod.getter("getAge", ageField));
        ctClass.addMethod(CtNewMethod.setter("setAge", ageField));
        ctClass.addMethod(CtNewMethod.getter("getPhone", phoneField));
        ctClass.addMethod(CtNewMethod.setter("setPhone", phoneField));

        //添加构造方法
        CtConstructor ctConstructor = new CtConstructor(new CtClass[]{}, ctClass);
        //为构造方法添加方法体
        StringBuffer buffer1 = new StringBuffer();
        //注意：this.age = ($w)18这里加上$w,因为age是Integer类型，是一个包装类，$w代表一个包装类型
        buffer1.append("{\nthis.age = ($w)18;\nthis.phone = \"15966668888\";\n}");
        ctConstructor.setBody(buffer1.toString());
        //把构造方法添加到新的类ctClass中
        ctClass.addConstructor(ctConstructor);

        //添加自定义方法
        CtMethod ctMethod = new CtMethod(CtClass.voidType, "printInfo", new CtClass[]{CtClass.intType}, ctClass);
        //为自定义的方法设置方法体
        StringBuffer buffer2 = new StringBuffer();
        buffer2.append("{");

        //buffer2.append("this.age.intValue() <= 18 ? System.out.println(20) : System.out.println(this.age);");

//        buffer2.append("if(this.age.intValue() > 18) {");
        buffer2.append("System.out.println(grade);");
        buffer2.append("if(grade > 18) {");
        buffer2.append("this.age = ($w)10;\nSystem.out.println(this.age);");
        buffer2.append("System.out.println(\"my age is \"+age);\n");
//        buffer2.append("long start = System.currentTimeMillis();\n");
//        buffer2.append("System.out.println(\"my phone is \"+phone);\n");
        buffer2.append("}");
        buffer2.append("}");
        ctMethod.setBody(buffer2.toString());
        ctClass.addMethod(ctMethod);


        //生成一个Class对象
        Class<?> clazz = ctClass.toClass();
        Object instance = clazz.newInstance();
        //反射 执行方法
        Method method = instance.getClass().getMethod("printInfo", new Class[]{int.class});
        method.invoke(instance, 1);


        Integer a = 1;

//        // 把生成的class文件写入文件
//        byte[] byteArr = ctClass.toBytecode();
//        FileOutputStream fos = new FileOutputStream(new File("./User.class"));
//        fos.write(byteArr);
//        fos.close();
    }


}
