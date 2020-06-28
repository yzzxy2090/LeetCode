package P_0581_Easy_最短无序连续子数组;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class Solution_Stack {
    public int findUnsortedSubarray(int[] nums) {
        if(nums == null || nums.length < 2) {
            return 0;
        }

        Deque<Integer> stack = new LinkedList<>();

        int start = nums.length - 1;
        int end = 0;

        for(int i =0; i<nums.length; i++) {
            while(!stack.isEmpty() && nums[i] < nums[stack.peek()]) {
                start = Math.min(stack.pop(), start);
            }
            stack.push(i);
        }

        stack.clear();
        for(int i=nums.length - 1; i>=0; i--) {
            while(!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                end = Math.max(stack.pop(), end);
            }
            stack.push(i);
        }

        return (end - start) < 0 ? 0 : (end - start + 1);
    }
}
