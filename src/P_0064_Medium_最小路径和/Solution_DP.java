package P_0064_Medium_最小路径和;

public class Solution_DP {
    public int minPathSum(int[][] grid) {
        if(grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;

        int[][] dp = new int[m][n];

        //从最右下角到最右下角的最短路径是确定的，就等于该位置值
        dp[m-1][n-1] = grid[m-1][n-1];

        //初始化dp最后一列，因为最后一列的位置只能向下走，此时该位置到右下角最短路径就是该位置的值+该位置下边一格到右下角最短路径dp[i+1][n-1]
        for(int i=m-2; i>=0; i--) {
            dp[i][n-1] = dp[i+1][n-1] + grid[i][n-1];
        }

        //初始化dp最后一行，因为最后一行的位置只能向右走，此时该位置到右下角最短路径就是该位置的值+该位置右边一格到右下角最短路径dp[m-1][i+1]
        for(int i=n-2; i>=0; i--) {
            dp[m-1][i] = dp[m-1][i+1] + grid[m-1][i];
        }

        /**
         * 对于一个普遍位置(i, j)
         * 该位置到右下角的最短路径由 其右边一格到右下角最短路径 以及 其下边一格到右下角最短路径 决定
         * 取两者中较短的+当前位置的值即为当前位置到右下角最短路径
         * dp[i][j] = grid[i][j] + Math.min(dp[i+1][j], dp[i][j+1])
         */
        for(int i=m-2; i>=0; i--) {
            for(int j=n-2; j>=0; j--) {
                dp[i][j] = grid[i][j] + Math.min(dp[i+1][j], dp[i][j+1]);
            }
        }

        return dp[0][0];
    }
}
