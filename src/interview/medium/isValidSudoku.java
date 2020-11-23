package interview.medium;
/*
Determine if a 9 x 9 Sudoku board is valid.
Only the filled cells need to be validated according to the following rules:
Each row must contain the digits 1-9 without repetition.
Each column must contain the digits 1-9 without repetition.
Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
Note:
A Sudoku board (partially filled) could be valid but is not necessarily solvable.
Only the filled cells need to be validated according to the mentioned rules.

 */
public class isValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        int n = board.length;
        int m = board[0].length;
        int[][] colMap = new int[n][m];
        int[][] rowMap = new int[n][m];
        int[][] matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] != '.') {
                    int row = i / 3;
                    int col = j / 3;
                    int matrixNum = row * 3 + col;
                    int num = board[i][j] - '0';
                    if (colMap[j][num - 1] != 0 || rowMap[i][num - 1] != 0 || matrix[matrixNum][num - 1] != 0) {
                        return false;
                    } else {
                        colMap[j][num - 1] = num;
                        rowMap[i][num - 1] = num;
                        matrix[matrixNum][num - 1] = num;
                    }
                }
            }
        }
        return true;
    }
}
