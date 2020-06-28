package P_0051_Hard_N皇后;

import java.util.ArrayList;
import java.util.List;

public class Solution_Advanced {
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

    private void dfs(char[][] board, int n, int curRow, List<List<String>> res) {

        if(curRow >= n) {
            res.add(printBoard(board));
            return;
        }

        for(int curCol=0; curCol<n; curCol++) {
            if(isValid(board, curRow, curCol)) {
                board[curRow][curCol] = 'Q';

                dfs(board, n, curRow + 1, res);

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

    private boolean isValid(char[][] board, int curRow, int curCol) {

        //对于当前列curCol从上到curRow这行检查这一列上有没有皇后
        for(int i=0; i<curRow; i++) {
            if(board[i][curCol] == 'Q') {
                return false;
            }
        }

        //从board[curRow][curCol]往左上对角线找有没有皇后
        for(int i=curRow-1, j=curCol-1; i>=0 && j>=0; i--, j--) {
            if(board[i][j] == 'Q') {
                return false;
            }
        }

        //从board[curRow][curCol]往右上对角线找有没有皇后
        for(int i=curRow-1, j=curCol+1; i>=0 && j<board[0].length; i--, j++) {
            if(board[i][j] == 'Q') {
                return false;
            }
        }

        return true;
    }
}
