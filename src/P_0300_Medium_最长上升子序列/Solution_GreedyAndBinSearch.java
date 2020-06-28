package P_0300_Medium_最长上升子序列;

public class Solution_GreedyAndBinSearch {
    public int lengthOfLIS(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }

        if(nums.length == 1) {
            return 1;
        }

        int len = nums.length;

        //tail[i]表示长度为i+1的上升子序列的末尾最小是哪个数字
        int[] tail = new int[len];
        tail[0] = nums[0];

        //end表示当前tail数组的有效末尾位置
        int end = 0;

        for(int i=1; i<len; i++) {

            //若当前遍历到的数字大于tail尾部数字，直接将当前数字加在tail数组尾部
            if(nums[i] > tail[end]) {
                end++;
                tail[end] = nums[i];
            }

            //若当前遍历到的数字小于等于tail尾部数字，说明该数字应该添加进tail数组内部，通过二分法找到该数字应该所处位置
            else {

                //二分法找到tail数组中第一个>=nums[i]的数字，将该数字替换为nums[i]
                int index = binSearch(tail, nums[i], 0, end);

                tail[index] = nums[i];
            }
        }
        //end指向上升子序列尾部，上升子序列长度为end+1
        return end + 1;
    }

    //找数组tail中第一个>=target的元素的下标
    private int binSearch(int[] tail, int target, int left, int right) {
        if(left >= right) {
            return left;
        }

        int mid = 0;

        while(left < right) {
            mid = left + (right - left) / 2;

            if(tail[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}
