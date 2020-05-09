package edu.zhanqian.leetcode;

/**
 * @Date 2020/4/23 15:01
 * @Created by zhanqian
 * @Description TODO
 */

public class Stage {

    /**
     * 三步问题。有个小孩正在上楼梯，楼梯有n阶台阶，小孩一次可以上1阶、2阶或3阶。实现一种方法，计算小孩有多少种上楼梯的方式。结果可能很大，你需要对结果模1000000007。
     * <p>
     * 示例1:
     * <p>
     * 输入：n = 3
     * 输出：4
     * 说明: 有四种走法
     * 示例2:
     * <p>
     * 输入：n = 5
     * 输出：13
     * 提示:
     * <p>
     * n范围在[1, 1000000]之间
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/three-steps-problem-lcci
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static int waysToStep(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        if (n == 3) return 4;

        long[] an = new long[n];
        an[0] = 1;
        an[1] = 2;
        an[2] = 4;

        for (int i = 3; i < n; i++) {
            an[i] = (an[i - 1] + an[i - 2] + an[i - 3]) % 1000000007;
        }
        return (int)an[n - 1];
    }

    public static int waysToStep2(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        if (n == 3) return 4;

        long[] dp = new long[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        for(int i = 4; i < n + 1; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 3]) % 1000000007;
        }
        return (int)dp[n];
    }

    public static void main(String[] args) {
//        System.out.println(waysToStep(100));
//        System.out.println(waysToStep2(100));

        int num[] = new int[]{2,1,3,4,8,1};
        System.out.println(massage(num));
    }

    public static int massage(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return nums[0];
        }
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[n - 1];
    }
}
