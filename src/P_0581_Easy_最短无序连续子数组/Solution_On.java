package P_0581_Easy_最短无序连续子数组;

public class Solution_On {
    public int findUnsortedSubarray(int[] nums) {
        if(nums == null || nums.length < 2) {
            return 0;
        }

        int start = 0;
        int end = 0;

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        /**
         * 例 2, 6, 4, 8, 10, 9, 15
         * 首先找到原数组在哪个位置开始不是升序的。
         * 然后找到非升序的序列中最大值和最小值
         * 从头开始遍历数组，一旦遇到降序的元素，记录第一个降序序列中较小的元素为 min，例中6, 4是第一个降序序列，把4记为min
         *                                记录最后一个降序序列中较大的元素为max，例中10, 9是最后一个降序序列，把10记为max
         */
        for(int i=0; i<nums.length-1; i++) {
            //降序序列中较小的值一定在后面，较大的值一定在前面
            if(nums[i+1] < nums[i]) {
                min = Math.min(min, nums[i+1]);
                max = Math.max(max, nums[i]);
            }
        }

        //从头到尾遍历数组，找到第一个大于非升序序列最小值min的元素的下标，记为start，即无序序列的起始位置
        for(int i=0; i<nums.length-1; i++) {
            if(nums[i] > min) {
                start = i;
                break;
            }
        }

        //从后往前遍历数组，找到第一个小于非升序序列最大值max的元素的下标，记为end，即无序序列的终止位置
        for(int i=nums.length - 1; i>=0; i--) {
            if(nums[i] < max) {
                end = i;
                break;
            }
        }

        return (end - start) <= 0 ? 0 : (end - start + 1);
    }
}
