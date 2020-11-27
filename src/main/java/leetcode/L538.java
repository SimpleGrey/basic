package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhanqian
 * @Date 2020/9/21 11:50
 * @Description TODO
 */
public class L538 {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        list.stream().parallel().forEach(e -> System.out.println(e));
    }

    int sum = 0;
    public TreeNode convertBST(TreeNode root) {
        zhongxu(root);
        return root;
    }

    private void zhongxu(TreeNode root) {
        if (root == null) {
            return;
        }
        zhongxu(root.right);
        root.val = root.val + sum;
        sum = root.val;
        zhongxu(root.left);
    }

}
