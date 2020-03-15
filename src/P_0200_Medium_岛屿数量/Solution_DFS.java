package P_0200_Medium_岛屿数量;

/**
 *
 * 时间复杂度 : O(M×N)，其中 M 和 N 分别为行数和列数。
 * 空间复杂度 : 最坏情况下为 O(M×N)，此时整个网格均为陆地，深度优先搜索的深度达到 M×N。
 */

public class Solution_DFS {
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;

        int res = 0;

        //遍历一遍矩阵，遇到岛屿，res就增长，然后进入感染函数
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(grid[i][j] == '1') {
                    res++;
                    infect(grid, m, n, i, j);
                }
            }
        }

        return res;
    }

    /**
     * 感染函数
     * @param grid   地图
     * @param m      地图整体有m行
     * @param n      地图整体有n列
     * @param curRow 当前遍历位置的行号
     * @param curCol 当前遍历位置的列号
     *
     * 进入该函数说明当前位置grid[curRow][curCol]可能需要被感染
     * 首先考察当前位置是否越界
     * 然后考察当前位置是否为未被感染的岛屿，即grid[curRow][curCol] == '1' ?
     * 是的话，将当前位置感染为'2'，grid[curRow][curCol] = '2'
     * 然后递归地尝试感染其周边四个方位的位置
     */
    private void infect(char[][] grid, int m, int n, int curRow, int curCol) {
        //递归到当前位置越界或当前位置不是1(要么是0，要么已被感染为2)
        if(curRow < 0 || curCol < 0 || curRow >= m || curCol >= n || grid[curRow][curCol] != '1') {
            return;
        }

        //将当前位置的1感染为2
        grid[curRow][curCol] = '2';

        //分别递归尝试感染上下左右，相当于dfs
        infect(grid, m, n, curRow, curCol+1);
        infect(grid, m, n, curRow+1, curCol);
        infect(grid, m, n, curRow, curCol-1);
        infect(grid, m, n, curRow-1, curCol);
    }
}
