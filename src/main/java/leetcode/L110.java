package leetcode;

import org.apache.hadoop.yarn.webapp.hamlet.Hamlet;

/**
 * @author zhanqian
 * @Date 2020/8/17 14:05
 * @Description TODO
 */
public class L110 {

    public static boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        int leftD = getDepth(root.left) + 1;
        int rightD = getDepth(root.right) + 1;
        if (Math.abs(leftD - rightD) > 1) {
            return  false;
        }
        return true;
    }

    private static int getDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }

        return Math.max(getDepth(node.left) + 1, getDepth(node.right) + 1);
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);

        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(2);

        treeNode.left.left = new TreeNode(3);
        treeNode.left.right = null;
        treeNode.right.left = null;
        treeNode.right.right = new TreeNode(3);

        treeNode.left.left.left = new TreeNode(4);
        treeNode.left.left.right = null;
        treeNode.left.right.left = null;
        treeNode.left.right.right = new TreeNode(4);
        System.out.println(isBalanced(treeNode));
    }
}
//[1, 2,2, 3,null,null,3, 4,null,null,4]