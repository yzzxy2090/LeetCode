package P_0074_Medium_搜索二维矩阵;

public class Solution_Test {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return false;
        }

        int m = matrix.length;
        int n = matrix[0].length;

        return binarySearch(matrix, m, n, 0, m * n - 1, target);
    }

    private boolean binarySearch(int[][] matrix, int m, int n, int start, int end, int target) {
        int mid;
        int midEle = 0;

        while(start + 1 < end) {
            mid = start + (end - start) / 2;
            midEle = matrix[mid / n][mid % n];

            if(midEle == target) {
                end = mid;
            } else if(midEle < target) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if(matrix[start / n][start % n] == target || matrix[end / n][end % n] == target) {
            return true;
        }

        return false;
    }
}
