package P_0239_Hard_滑动窗口最大值;

import java.util.Deque;
import java.util.LinkedList;

public class Solution_Execise {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length < 1 || k < 1) {
            return new int[0];
        }

        Deque<Integer> queue = new LinkedList<Integer>();
        int turns = nums.length - k + 1;
        int[] res = new int[turns];

        for (int i = 0; i < nums.length; i++) {
            while (!queue.isEmpty() && nums[queue.peekLast()] < nums[i]) {
                queue.pollLast();
            }

            queue.addLast(i);

            if (queue.peekFirst() <= i - k) {
                queue.pollFirst();
            }

            if (i >= k - 1) {
                res[i - k + 1] = nums[queue.peekFirst()];
            }
        }
        return res;
    }
}
