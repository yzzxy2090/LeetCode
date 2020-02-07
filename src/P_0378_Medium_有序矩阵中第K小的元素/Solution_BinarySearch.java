package P_0378_Medium_有序矩阵中第K小的元素;

/**
 * 378. Kth Smallest Element in a Sorted Matrix (Medium)
 *
 * 方法一：二分查找
 *
 * 设第k小元素为x
 * 则matrix中，<=x的元素个数为k
 * 根据这一性质进行二分查找
 */

public class Solution_BinarySearch {
    public int kthSmallest(int[][] matrix, int k) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return Integer.MIN_VALUE;
        }

        int m = matrix.length, n = matrix[0].length;
        int left = matrix[0][0], right = matrix[m - 1][n - 1];

        while(left < right) {
            int mid = (left + right) >>> 1;

            //每次循环中找到matrix中<=mid的元素值的个数count
            int count = notBiggerThanMid(matrix, m, n, mid);

            /**
             * 若count<k，说明第k小元素>mid，它在(mid, right]范围里
             * 若count>=k，说明第k小元素<=mid，它在[left, mid]范围里
             *
             * 最终当left==right时，他们共同指向的元素就是第k小元素
             */
            if(count < k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right;
    }

    private int notBiggerThanMid(int[][] matrix, int m, int n, int mid) {
        //i为当前行号，初始为最后一行，j为当前列号，初始为第一列
        int i = m - 1;
        int j = 0;

        //count用于记录matrix中<=mid的元素的个数
        int count = 0;

        /**
         * 从矩阵左下角开始，逐列找到每列最后一个<=mid的值，那么就可以知道当前列有多少个元素<=mid
         *
         * 当matrix[i][j]>mid时，
         * 当前的第i行从第j列一直到最后的元素就不用考虑了
         * 因为后面的元素只会比当前matrix[i][j]大，意味着更大于mid
         * 此时遍历到上一行，i--
         *
         * 当matrix[i][j]<=mid时，
         * 说明当前matrix[i][j]是当前第j列<=mid值中最大的一个
         * 该列中matrix[i][j]上方的元素小于当前遍历值，一定也<=mid
         * 该列中<=mid的元素个数为i+1个，更新count值
         * 此时该列统计完毕，往右遍历一列，j++
         */
        while(i >= 0 && j < n) {
            if(matrix[i][j] <= mid) {
                j++;
                count += i + 1;
            } else {
                i--;
            }
        }
        return count;
    }
}
