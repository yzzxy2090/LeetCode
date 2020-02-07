package P_0074_Medium_搜索二维矩阵;

/**
 * 74. Search a 2D Matrix (Medium)
 *
 * 二分查找
 * 该题的m*n的矩阵可看做一个长度为m*n的有序数组
 * 数组的下标可以由下式方便地转化为原矩阵中的行号和列号，该有序数组非常适合二分查找。
 * row = index / n, col = index % n
 *
 *
 * 这是一个标准二分查找算法 :
 *
 *
 * 初始化左右序号，left = 0 和 right = m x n - 1。
 *
 * While left < right :
 *
 * Step1.选取虚数组最中间的序号作为中间序号: mid = (left + right) / 2。
 *
 * Step2.该序号对应于原矩阵中的row = mid / n行 col = mid % n 列, 由此可以拿到中间元素midEle，该元素将虚数组分为两部分。
 *
 * Step3.比较 midEle 与 target 以确定在哪一部分进行进一步查找。
 */

public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return false;
        }

        int m = matrix.length;
        int n = matrix[0].length;

        int left = 0;
        int right = m * n - 1;

        //方法内变量声明时可以暂时不赋值，但使用时必须赋值，这里在while循环中赋值
        int mid;
        int midEle;

        while(left <= right) {
            mid = (left + right) >>> 1;
            midEle = matrix[mid / n][mid % n];

            /**
             * 若当前midEle == target，匹配成功，直接返回true
             * 若当前midEle < target，说明应该去midEle右边范围找target，把左边界置于midEle右侧，left = mid + 1;
             * 若当前midEle > target，说明应该去midEle左边范围找target，把右边界置于midEle左侧，right = mid - 1;
             */
            if(midEle == target) {
                return true;
            } else {
                if(midEle < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return false;
    }
}
