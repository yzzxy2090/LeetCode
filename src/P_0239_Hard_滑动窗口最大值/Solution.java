package P_0239_Hard_滑动窗口最大值;

/**
 * 239. Sliding Window Maximum (Hard)
 * <p>
 * 方法一：双向队列法
 * 遍历数组，将元素从队尾入队
 * 1.若此时队列不为空，且队列中某些(或全部)现有元素小于当前遍历到的元素，则它们一定不可能是滑动窗口中的最大值，
 * 先将它们从队尾出队，再将当前遍历到的元素从队尾入队
 * 2.除了情况1，当前遍历到的元素直接入队，虽然它目前不是窗口中最大元素，但在将来它有可能是
 * 3.若遍历到当前元素时，此时双向队列中队首元素已经滑出窗口，就要将其从队首出队
 * 4.注意，遍历过的元素长度i+1大于等于窗口宽度k时要记录滑动窗口的最大值，即队首元素
 * 让双向队列队首始终为窗口中的最大值
 * <p>
 * 另外，队列中保存的是元素的数组下标值
 *
 * 时间复杂度：O(N)，每个元素被处理两次- 其索引被添加到双向队列中和被双向队列删除。
 * 空间复杂度：(N)，输出数组使用了 O(N−k+1) 空间，双向队列使用了 O(k)。
 */

import java.util.Deque;
import java.util.LinkedList;

public class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {

        if (nums == null || nums.length < 1 || k <= 0) {
            return new int[0];
        }

        /**
         * 用一个双端队列模拟滑动窗口，存储数组中元素的下标值
         * 队首元素永远是当前窗口中最大值的数组下标
         */
        Deque<Integer> queue = new LinkedList<Integer>();

        int turns = nums.length - k + 1;
        int[] res = new int[turns];

        for (int i = 0; i < nums.length; i++) {
            /**
             * 在滑动过程中
             * 如果双端队列尾部元素小于当前遍历到的元素，那么它一定不可能是滑动窗口中的最大值，从尾部出队
             * 直至当前遍历到的元素nums[i]在队列中处于队头或仅小于其前一个元素
             */
            while (!queue.isEmpty() && nums[queue.peekLast()] < nums[i]) {
                queue.pollLast();
            }

            //如果双端队列为空或尾部元素大于当前遍历到的元素，那么它未来有可能是滑动窗口中的最大值，从尾部入队
            queue.addLast(i);

            //遍历到当前元素nums[i]时，窗口队首元素已滑出窗口范围，要从队首将其出队
            if (queue.peekFirst() <= i - k) {
                queue.pollFirst();
            }

            //从当前遍历到的i=(k-1)时开始记录滑动窗口中的最大元素
            if (i >= k - 1) {
                res[i - k + 1] = nums[queue.peekFirst()];
            }
        }

        return res;
    }
}
