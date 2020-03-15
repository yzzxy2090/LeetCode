package P_0064_Medium_最小路径和;

/**
 * 暴力dfs递归
 */

public class Solution_Recursion {
    public int minPathSum(int[][] grid) {
        if(grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;
        return dfs(grid, m, n, 0, 0);
    }

    private int dfs(int[][] grid, int m, int n, int i, int j) {
        //当前已来到右下角位置，当前位置到右下角最短路径就是当前位置的值
        if(i == m-1 && j == n-1) {
            return grid[i][j];
        }

        //如果已经来到最后一行，只能向右走，当前位置最短路径就是当前位置的值+当前位置右边一格走到右下角的最短路径
        if(i == m-1) {
            return grid[i][j] + dfs(grid, m, n, i, j+1);
        }

        //如果已经来到最后一列，只能向下走，当前位置最短路径就是当前位置的值+当前位置下边一格走到右下角的最短路径
        if(j == n-1) {
            return grid[i][j] + dfs(grid, m, n, i+1, j);
        }

        //否则分别考察当前位置右边一格和下边一格走到右下角的最短路径，取两者中较短的+当前位置值返回
        int rightPath = dfs(grid, m, n, i, j+1);
        int downPath = dfs(grid, m, n, i+1, j);

        return grid[i][j] + Math.min(rightPath, downPath);
    }
}
