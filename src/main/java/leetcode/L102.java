package leetcode;

import org.apache.spark.sql.sources.In;
import org.mortbay.util.ajax.JSON;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Date 2020/5/13 11:57
 * @Created by zhanqian
 * @Description TODO
 */
public class L102 {

    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> listList = new ArrayList<>();
        if (root == null) {
            return listList;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.poll();
            int val = treeNode.val;
            List<Integer> items = new ArrayList<>();
            items.add(val);
            if (treeNode.left != null) {
                queue.add(treeNode.left);
            }
            if (treeNode.right != null) {
                queue.add(treeNode.right);
            }

        }


        return listList;
    }


    /**
     * 广度优先搜索 BFS  原始解法
     *
     * @param root
     * @return
     */

    public static List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> listList = new ArrayList<>();
        if (root == null) return listList;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                list.add(poll.val);

                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
            }
            listList.add(list);
        }

        return listList;
    }

    public static void main(String[] args) {
        TreeNode n = new TreeNode(3);
        TreeNode n1 = new TreeNode(9);
        TreeNode n2 = new TreeNode(20);
        n.left = n1;
        n.right = n2;
        TreeNode n3 = new TreeNode(15);
        TreeNode n4 = new TreeNode(7);
        n2.left = n3;
        n2.right = n4;

        System.out.println(JSON.toString(levelOrder(n)));
    }
}

