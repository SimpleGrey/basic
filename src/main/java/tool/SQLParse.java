package tool;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * @Date 2020/3/5 13:56
 * @Created by zhanqian
 * @Description TODO
 */
public class SQLParse {

    public static List<String> concats = Arrays.asList(new String[]{"|", "&", "!"});

    public static void main(String[] args) {

//        Stack stack = new Stack<>();
//        //1.empty()栈是否为空
//        System.out.println(stack.empty());
//        //2.peek()栈顶值    3.进栈push()
//        stack.push(new Integer(1));
//        stack.push("b");
//        System.out.println(stack.peek());`
//        //4.pop()出栈
//        stack.pop();
//        System.out.println(stack.peek());

        System.out.println("\n\n0----------");
        sql1("(!(sex='male' & rc>10) & age>18)");
        System.out.println("\n\n1----------");
        sql1("(!(sex='male' & rc>10) & (age>18 & rc>2 ))");
        System.out.println("\n\n2----------");
        sql1("(!(sex='male' & rc>10 | (age=10 & vc>2)) & (age>18 & rc>2 ))");
        System.out.println("\n\n3----------");
        sql1("(!(sex='male' & rc>10 | (age=10 & vc>2)) & (age>18 & rc>2 ))");
        System.out.println("\n\n4----------");
        sql1("((age>18 & rc>2) & !(sex='male' & rc>10 | (age=10 & vc>2)))");
        System.out.println("\n\n5----------");
        sql1("((age>18 & rc>2) & !((age=10 & vc>2) | sex='male' & rc>10))");
        System.out.println("\n\n6----------");
        sql1("(age>18 & rc>2)");
        System.out.println("\n\n7----------");
        sql1("((age>18 & rc>2) & !((age=10 & vc>2) | sex='male' & rc>10)  & (age>18 & rc>2))");
        System.out.println("\n\n8----------");
        sql1("((age>18 & rc>2) & !((age=10 & vc>2) | sex='male' & rc>10 & (age=17 & bc<1))  & (age>18 & rc>2))");
        System.out.println("\n\n9----------");
        sql1("((age>18 & rc>2) & !((age=10 & vc>2) & (age=17 & bc<1) | sex='male' & rc>10 )  & (age>18 & rc>2))");
    }


    // ！（sex='male' & rc>10） & age>18
    // ！((sex='male' & rc<5) & (age>10 & vc>10))
    public static List<String> sql1(String sql) {
        List<String> list = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        char[] chars = sql.toCharArray();
        for (char e : chars) {

            //如果不是) 说明最小语句还没执行完
            if (e != ')') {
                stack.push(String.valueOf(e));
                continue;
            }

            //遇到')'弹出 第一个')'之前所有数据
            StringBuffer s = new StringBuffer(")");
            while (!stack.empty()) {
                String stack_elem = stack.pop();
                s.append(stack_elem);

                if (stack.empty()) {
                    break;
                }

                //遇到最近的一个'）'
                if (StringUtils.equals("(", stack_elem)) {
                    String extra_elem = stack.pop();

                    //如果是!那么 !(...)是一起的，所以需要都取出来
                    //如果不是!，那么不是(...)的一部分，所以不需要取出来，所以放回去
                    if (StringUtils.equals("!", extra_elem)) {
                        s.append(extra_elem);
                    } else {
                        stack.push(extra_elem);
                    }

                    break;
                }
            }
            String string = s.reverse().toString();
            System.out.println(string);
            list.add(string);

            //再申请一个栈，顺序压入，遇到不完整的表达式 比如右边缺失
            Stack<String> stack2 = new Stack<>();
            for (String s1 : list) {
                if (!isMerge(s1)) {
                    stack2.push(s1);
                    //可以进行计算了 获得分布式计算数据 获取计算结果N
                    continue;
                }

                //如果遇到需要合并的表达式
                //取出来根据缺失的位置的N个，弹出N个元素,缺失的位置从右边开始补充

            }
        }
        return list;
    }

    /**
     * (age>18 & rc>2)
     * (age=10 & vc>2)
     * !(sex='male' & rc>10 || )
     * ( & )
     *
     * (age>18 & rc>2)
     * (age=10 & vc>2)
     * !( || sex='male' & rc>10)
     * ( & )
     */
    //判断返回的数据是否是需要合并的。
    private static boolean isMerge(String sql) {
        String s1 =  sql.replaceAll("!", "").replaceAll("\\(", "").replaceAll("\\)", "").trim();
        String s_begin = s1.substring(0, 1);
        String s_end = s1.substring(s1.length() - 1, s1.length());
        return concats.contains(s_begin) || concats.contains(s_end);
    }

    //!( &  | sex='male' & rc>10 )
    //判断获取空缺的位置个数 以及 填充的位置

}
