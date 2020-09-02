package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhanqian
 * @Date 2020/8/25 17:56
 * @Description TODO
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class L491 {

    public static void main(String[] args) {
        Integer[] arr = {3, 9, 20, null, null, 15, 7};
    }

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        Boolean balance = Math.abs(getDepth(root.left) - getDepth(root.right)) < 2;
        return balance && isBalanced(root.right) && isBalanced(root.left);
    }

    private int getDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return Math.max(getDepth(node.left), getDepth(node.right)) + 1;
    }

}
