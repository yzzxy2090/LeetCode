package P_0051_Hard_N皇后;

import java.util.ArrayList;
import java.util.List;

public class Solution_Col {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        if(n <= 0) {
            return res;
        }

        char[][] board = new char[n][n];
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                board[i][j] = '.';
            }
        }

        dfs(board, n, 0, res);

        return res;
    }

    /**
     * @param board    棋盘
     * @param n        棋盘边长
     * @param curCol   当前是准备放第几行的皇后
     * @param res      结果集
     */
    private void dfs(char[][] board, int n, int curCol, List<List<String>> res) {

        //递归截止条件
        if(curCol >= board[0].length) {
            //add res
            res.add(printBoard(board));

            return;
        }

        /**
         * 当前向要摆放第curCol列的皇后
         * 考察该列的每一行，是否是一个可摆放位置
         * 如果能摆放，则摆放皇后，往下一层递归
         * 递归完成后还要把这层摆放的皇后拿掉，以实现回溯
         */
        for(int curRow=0; curRow<board[0].length; curRow++) {

            if(isValid(board, curRow, curCol)) {

                board[curRow][curCol] = 'Q';

                dfs(board, n, curCol + 1, res);

                //还原棋盘从而实现回溯，下一步会考察下一列能否摆皇后的情况
                board[curRow][curCol] = '.';
            }
        }

    }

    private List<String> printBoard(char[][] board) {
        List<String> res = new ArrayList<>();
        String temp;
        for(int i=0; i<board.length; i++) {
            temp = String.valueOf(board[i]);
            res.add(temp);
        }
        return res;
    }

    /**
     * 判断当前位置board[curRow][curCol]能否摆一个皇后
     *
     * 外层循环从第一列遍历到当前列
     * 内层循环从第一行遍历到最后一行
     * 查看每个位置是否会导致当前位置curRow curCol无法放皇后
     *
     * 若遍历的当前位置board[i][j]是一个皇后，即board[i][j]=='Q'
     *      而当前要判断的位置board[curRow][curCol]与该皇后处于同一行上，即&& curRow==j
     *      或者当前要判断位置board[curRow][curCol]与该皇后处于同一主/副对角线，即Math.abs(curRow - i) == Math.abs(curCol - j)
     *      (判断两个点是否在同一主副对角线即，判断两个点的连线是否为y=x或y=-x
     *       对于两点(x1, y1)和(x2, y2)，
     *       两点在同一主对角线：x1-x2=y1-y2
     *       两点在同一副对角线：x1-x2=-(y1-y2)
     *       合并判断即|x1-x2|=|y1-y2|)
     * 这种情况下说明当前位置board[curRow][curCol]不能放皇后
     *
     * 若遍历完后没有出现冲突，则说明当前位置board[curRow][curCol]可以放皇后
     */
    private boolean isValid(char[][] board, int curRow, int curCol) {
        for(int i=0; i<=curCol; i++) {
            for(int j=0; j<board.length; j++) {
                if(board[j][i] == 'Q'
                        && (curRow == j || Math.abs(curRow - j) == Math.abs(curCol - i))) {
                    return false;
                }
            }
        }
        return true;
    }
}
