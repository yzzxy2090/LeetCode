package P_0200_Medium_岛屿数量;

import java.util.LinkedList;
import java.util.Queue;

public class Solution_BFS {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;
        int res = 0;

        int removeIndex = 0;
        int row = 0, col = 0;

        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {

                    res++;
                    grid[i][j] = '0';

                    /**
                     * 将当前节点加入队列
                     * 队列中存矩阵坐标，可以将矩阵坐标(i, j)转化为一维数组坐标(i*n+j)
                     */
                    q.offer(i * n + j);

                    /**
                     * 遍历完当前节点grid[i][j]后将队首元素出队
                     * 并将其周围是岛屿的节点入队，并将这些节点也置为已访问
                     */
                    while (!q.isEmpty()) {
                        removeIndex = q.poll();
                        row = removeIndex / n;
                        col = removeIndex % n;

                        //访问当前位置上边一格
                        if ((row - 1) >= 0 && grid[row - 1][col] == '1') {
                            q.offer((row - 1) * n + col);
                            grid[row - 1][col] = '0';
                        }

                        //访问当前位置左边一格
                        if ((col - 1) >= 0 && grid[row][col - 1] == '1') {
                            q.offer(row * n + col - 1);
                            grid[row][col - 1] = '0';
                        }

                        //访问当前位置下边一格
                        if ((row + 1) < m && grid[row + 1][col] == '1') {
                            q.offer((row + 1) * n + col);
                            grid[row + 1][col] = '0';
                        }

                        //访问当前位置右边一格
                        if ((col + 1) < n && grid[row][col + 1] == '1') {
                            q.offer(row * n + col + 1);
                            grid[row][col + 1] = '0';
                        }
                    }
                }
            }
        }

        return res;

    }
}
