package P_0052_Hard_N皇后II;

public class Soluiton {

    public int totalNQueens(int n) {
        if(n <= 0) {
            return 0;
        }

        char[][] board = new char[n][n];
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                board[i][j] = '.';
            }
        }

        int[] res = new int[1];

        dfs(board, n, 0, res);

        return res[0];
    }

    private void dfs(char[][] board, int n, int curRow, int[] res) {

        if(curRow >= n) {
            res[0]++;
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

    private boolean isValid(char[][] board, int curRow, int curCol) {

        //对于当前列curCol从上到curRow这行检查这一列上有没有皇后
        for(int i=0; i<board.length; i++) {
            if(board[i][curCol] == 'Q') {
                return false;
            }
        }

        for(int i=curRow-1, j=curCol-1; i>=0 && j>=0; i--, j--) {
            if(board[i][j] == 'Q') {
                return false;
            }
        }

        for(int i=curRow-1, j=curCol+1; i>=0 && j<board[0].length; i--, j++) {
            if(board[i][j] == 'Q') {
                return false;
            }
        }

        return true;
    }
}
