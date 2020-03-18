package druid;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.expr.SQLBinaryOpExpr;
import com.alibaba.druid.sql.ast.expr.SQLUnaryExpr;
import org.apache.commons.lang3.StringUtils;

import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2020/3/6 14:29
 * @Created by zhanqian
 * @Description TODO
 */
public class TreeTransform {

    private Node root = null;

    /**
     * 后序遍历:DEBCA
     *
     * @return
     */
    public boolean postorderTarverse(Node node) {
        if (node.lChild != null) {
            postorderTarverse(node.lChild);
        }
        if (node.rChild != null) {
            postorderTarverse(node.rChild);
        }
        list.add(node);
        return true;
    }

    public List<Node> getList() {
        return list;
    }

    private class Node {

        //操作符号
        private String op;

        //运算数据最小sql
        private String data;

        //左节点
        private Node lChild;

        //右节点
        private Node rChild;

        public Node() {
        }

        public Node(String op, String data, Node lChild, Node rChild) {
            this.op = op;
            this.data = data;
            this.lChild = lChild;
            this.rChild = rChild;
        }
    }

    private List<Node> list = new ArrayList<>();

    public static void main(String[] args) {
        SQLExpr sqlExpr = MySQLParser.get();
        new TreeTransform().run(sqlExpr);
    }

    public Node run(SQLExpr sqlExpr) {
        Node node = new Node();
        newTree(sqlExpr, node);
        System.out.println("...");
        return node;
    }

    public boolean newTree(SQLExpr sqlExpr, Node node) {
        if (sqlExpr instanceof SQLBinaryOpExpr) {
            SQLBinaryOpExpr sqlBinaryOpExpr = (SQLBinaryOpExpr) sqlExpr;

            //转为不换行的sql
            String whereString = sqlBinaryOpExpr.toString().replaceAll("\n", " ");

            //包含() 说明有优先级顺序，所以我们需要再拆分。
            if (StringUtils.contains(whereString, "(")) {
                SQLExpr left = sqlBinaryOpExpr.getLeft();
                SQLExpr right = sqlBinaryOpExpr.getRight();
                Node leftNode = new Node();
                Node rightNode = new Node();

                //绑定node父子关系
                node.lChild = leftNode;
                node.rChild = rightNode;
                if (node.op == null ) {
                    node.op = sqlBinaryOpExpr.getOperator().toString();
                } else {
                    node.op = node.op + " " + sqlBinaryOpExpr.getOperator().toString();
                }

                //递归遍历
                newTree(left, leftNode);
                newTree(right, rightNode);
            } else {
                //不包含() 说明有优先级顺序，所以我们需要再拆分。
                node.data = whereString;
            }
        }

        if (sqlExpr instanceof SQLUnaryExpr) {
            SQLUnaryExpr sqlUnaryExpr = (SQLUnaryExpr) sqlExpr;
            SQLBinaryOpExpr sqlBinaryOpExpr = (SQLBinaryOpExpr) sqlUnaryExpr.getExpr();
            node.op = "Not";
            //递归遍历
            newTree(sqlBinaryOpExpr, node);
        }
        return true;
    }

    /**
     * 前序遍历:ABDEC
     * 1.首先遍历左子树,遍历到叶子结点为止
     *
     * @return
     */
    public boolean preorderTraverse(Node node) {
        //首先从根节点开始遍历
        list.add(node);
        if (node.lChild != null) {
            preorderTraverse(node.lChild);
        }
        if (node.rChild != null) {
            preorderTraverse(node.rChild);
        }
        return true;
    }
}
