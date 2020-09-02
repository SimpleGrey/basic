package leetcode;

import com.alibaba.fastjson.JSON;

import java.util.*;

/**
 * @Date 2020/5/13 14:26
 * @Created by zhanqian
 * @Description TODO
 */
public class L130 {


    public static void main(String[] args) {
        int[] arr = {2,2,2,2,5,5,5,8};
        System.out.println(numOfSubarrays(arr, 3, 4));
    }

//    给你一个整数数组 arr 和两个整数 k 和 threshold 。
//    请你返回长度为 k 且平均值大于等于 threshold 的子数组数目。
    public static int numOfSubarrays(int[] arr, int k, int threshold) {
        int length = arr.length;
        if (length < k) return 0;

        int initSUM = 0;
        for (int i = 0; i < k-1; i++) {
            initSUM += arr[i];
        }

        int count = 0;
        threshold = threshold * k;
        for (int i = k - 1; i < length; i++) {
            initSUM = initSUM + arr[i];
            if (initSUM >= threshold) {
                count++;
            }
            initSUM = initSUM - arr[i - k + 1];
        }

        return count;
    }

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) {
            return null;
        }

        TreeNode root = new TreeNode(0);

        List<Integer> pre = new ArrayList<>();
        List<Integer> in = new ArrayList<>();

        for (int i = 0; i < preorder.length; i++) {
            pre.add(preorder[i]);
        }
        for (int i = 0; i < inorder.length; i++) {
            in.add(inorder[i]);
        }


        build(root, pre, in);

        return root;
    }

    public static void build(TreeNode node, List<Integer> pre, List<Integer> in) {
        if (pre.size() == 1) {
            node.val = pre.get(0);
            return ;
        }
        if (pre.size() == 0) {
            return ;
        }

        int preV = pre.get(0);
        node.val = preV;

        //pre左树开始下标
        int preLeftTreeStartIndex = 1;

        //in左树开始下标
        int inLeftTreeStartIndex = 0;

        //in左树结束下标
        int inLeftTreeEndIndex = in.indexOf(preV) - 1;

        //pre左树结束下标
        int preLeftTreeEndIndex = -1;
        if (inLeftTreeEndIndex >= 0) {
            preLeftTreeEndIndex = pre.indexOf(in.get(inLeftTreeEndIndex));
        }

        //in右树开始下标
        int inRightTreeStartIndex = in.indexOf(preV) + 1;

        //in右树结束下标
        int inRightTreeEndIndex = in.size();

        //pre右树结束下标
        int preRightTreeEndIndex = pre.size();

        //pre右树开始下标
        int preRightTreeStartIndex = 1;
        if (preLeftTreeEndIndex >= 0) {
            preRightTreeStartIndex = preLeftTreeEndIndex + 1;
        }

        if (preLeftTreeEndIndex < pre.size() && inLeftTreeEndIndex < in.size() && inLeftTreeEndIndex >= 0 ) {
            TreeNode left = new TreeNode(0);
            node.left = left;
            build(left, pre.subList(preLeftTreeStartIndex, preLeftTreeEndIndex + 1), in.subList(inLeftTreeStartIndex, inLeftTreeEndIndex + 1));
        }

        if (preRightTreeStartIndex < pre.size() && inRightTreeStartIndex < in.size() && preRightTreeStartIndex >= 0 ) {
            TreeNode right = new TreeNode(0);
            node.right = right;
            build(right, pre.subList(preRightTreeStartIndex, preRightTreeEndIndex), in.subList(inRightTreeStartIndex, inRightTreeEndIndex));
        }
    }
}
