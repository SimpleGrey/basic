package leetcode;

import com.alibaba.fastjson.JSON;
import javafx.scene.layout.Priority;
import org.json4s.jackson.Json;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author zhanqian
 * @Date 2020/8/17 10:57
 * @Description TODO
 */
public class KthLargest {

    Queue<Integer> queue = new PriorityQueue<>();
    private Integer k;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        for (int i = 0; i < nums.length; i++) {
            queue.add(nums[i]);
            if (queue.size() > k) {
                queue.poll();
            }
        }
    }

    public int add(int val) {
        queue.add(val);
        if (queue.size() > k) {
            queue.poll();
        }
        return queue.peek();
    }

    public static void main(String[] args) {
        int k = 3;
        int[] arr = {4, 5, 8, 2};
        KthLargest kthLargest = new KthLargest(3, arr);
        System.out.println(kthLargest.add(3));
        System.out.println(kthLargest.add(5));
        System.out.println(kthLargest.add(10));
        System.out.println(kthLargest.add(9));
        System.out.println(kthLargest.add(4));

       
    }
}
