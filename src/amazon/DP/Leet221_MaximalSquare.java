package amazon.DP;

public class Leet221_MaximalSquare {
    public int maximalSquare(char[][] matrix) {
        /*
        -> largest square
            left upper
                check each right bottom
            TC: O(n * m * Math.min(m^2, n^2))

            1 1 1
            1 2 2
            1 2 3
            for each cell
                if cell == 1
                    matrix[i][j] = Math.min(up, left, leftupper) + 1
        */

        int n = matrix.length;
        int m = matrix[0].length;
        int[][] dp = new int[n + 1][m + 1];
        int res = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                    res = Math.max(res, dp[i][j]);
                }
            }
        }
        return res * res;
    }
}
