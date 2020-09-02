package leetcode;


import java.util.*;

/**
 * @author zhanqian
 * @Date 2020/8/27 9:15
 * @Description TODO
 */
public class L107 {

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
    }

    //1. 队列，每次取完所有的数据，遍历 --> 正序 [3,9,20,null,null,15,7]
    //2. 递归记录深度
    Map<Integer, List<Integer>> map = new HashMap<>();
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        cy(root, 0);
        Integer maxI = Integer.MIN_VALUE;
        for (Integer i : map.keySet()) {
            if (i > maxI) {
                maxI = i;
            }
        }
        List<List<Integer>> listList = new ArrayList<>();
        for (int i = maxI; i >= 0; i--) {
            listList.add(map.get(i));
        }
        return listList;
    }

    private void cy(TreeNode root, int i) {
        if (root == null) {
            return;
        }
        if (map.containsKey(i)) {
            map.get(i).add(root.val);
        } else {
            List<Integer> list = new ArrayList<>();
            list.add(root.val);
            map.put(i, list);
        }
        cy(root.left, i + 1);
        cy(root.right, i + 1);
    }

    public List<List<Integer>> levelOrderBottom2(TreeNode root) {
        Queue<TreeNode> treeNodeQueue = new LinkedList<>();
        LinkedList<List<Integer>> list = new LinkedList<>();
        treeNodeQueue.add(root);
        while (!treeNodeQueue.isEmpty()) {
            int size = treeNodeQueue.size();
            List<Integer> subList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = treeNodeQueue.poll();
                subList.add(treeNode.val);
                if (treeNode.left != null) {
                    treeNodeQueue.add(treeNode.left);
                }
                if (treeNode.right != null) {
                    treeNodeQueue.add(treeNode.right);
                }
            }
            list.addFirst(subList);
        }

        return list;
    }

}
