package P_0240_Medium_搜索二维矩阵II;

/**
 * 240. Search a 2D Matrix II (Medium)
 *
 * 因为矩阵的行和列是排序的（分别从左到右和从上到下），所以在查看任何特定值时，我们可以修剪一整行或一整列元素。
 *
 * 算法：
 * 首先，我们初始化一个遍历指针指向矩阵左下角 (matrix.length-1，0) 。
 *
 * 然后，直到找到目标并返回 true（或者指针指向矩阵维度之外的为止，我们执行以下操作：
 * 如果当前指向的值大于目标值，则指针"向上"移动一行。
 * 否则，如果当前指向的值小于目标值，则指针"向右"移动一列。
 *
 * 不难理解为什么这样做永远不会删减正确的答案；
 * 因为行是从左到右排序的，所以我们知道当前值右侧的每个值都较大。
 * 因此，如果当前值已经大于目标值，我们知道它右边的每个值会比较大。
 * 也可以对列进行非常类似的论证，因此这种搜索方式将始终在矩阵中找到目标（如果存在）。
 *
 * 关于出发点的选择：
 * 选左上角，往右走和往下走都增大，不能选
 * 选右下角，往上走和往左走都减小，不能选
 * 选左下角，往右走增大，往上走减小，可选
 * 选右上角，往下走增大，往左走减小，可选
 */

public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int m = matrix.length, n = matrix[0].length;
        int curRow = m - 1, curCol = 0;

        while(curRow >= 0 && curCol < n) {
            if(matrix[curRow][curCol] > target) {
                curRow--;
            } else if (matrix[curRow][curCol] < target) {
                curCol++;
            } else {
                return true;
            }

        }
        return false;
    }
}
