package intuit.validMatrix;

import java.util.ArrayList;

import java.util.List;

public class Q2 {
    static class Continuous {
        int x;  //horizontal
        int y;  //vertical
        public Continuous(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static boolean checkNonogram(int[][] matrix, int[][] rows, int[][] cols) {
    /*
     matrix1 =
       [[1,1,1,1],
        [0,1,1,1],
        [0,1,0,0],
        [1,1,0,1],
        [0,0,1,1]]
     rows1_1    =  [], [1], [1,2], [1], [2]
     columns1_1 =  [2,1], [1], [2], [1]
        for each coordinate
            ending at i,j
                    has how many continuous zeros of rows
                    ....                             cols
           continuous dp[i][j]
                 if (matrix[i][j] == 0) {
                    dp[i][j].h = dp[i][j - 1] + 1
                    dp[i][j].v = dp[i - 1][j] + 1
                 } else
                    dp[i][j] = new Coordinate(0, 0)
           continuous<h,v>
        ==>
            [[1,1,1,1],
            [0,1,1,1],
            [<1,2>,1,0,<2,1>],   <- [2,1] is reverse of [1,2]
            [1,1,0,1],
            [0,0,1,1]]

            ^
            |
            [1, 2] reverse [1, 2]
     */

        int n = matrix.length;
        int m = matrix[0].length;
        Continuous[][] dp = new Continuous[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 1) {
                    dp[i][j] = new Continuous(0, 0);
                } else {
                    if (i == 0 && j == 0) {
                        dp[i][j] = new Continuous(1, 1);
                    } else if (i == 0) {
                        dp[i][j] = new Continuous(dp[i][j - 1].x + 1, 1);
                    } else if (j == 0) {
                        dp[i][j] = new Continuous(1, dp[i - 1][j].y + 1);
                    } else {
                        dp[i][j] = new Continuous(dp[i][j - 1].x + 1, dp[i - 1][j].y + 1);
                    }
                }
            }
        }
        // check rows
        for (int i = 0; i < n; i++) {
            List<Integer> ans = new ArrayList<>();
            for (int j = m - 1; j >= 0; j--) {
                if (j == m - 1 && dp[i][j].x > 0 || j < m - 1 && dp[i][j].x > 0 && dp[i][j + 1].x < dp[i][j].x) {
                    ans.add(dp[i][j].x);
                }
            }
            if (ans.size() != rows[i].length) return false;
            int len = ans.size();
            for (int k = 0; k < len; k++) {
                if (rows[i][k] != ans.get(len - k - 1)) {
                    return false;
                }
            }
        }
        //check cols
        for (int j = 0; j < m; j++) {
            List<Integer> ans = new ArrayList<>();
            for (int i = n - 1; i >= 0; i--) {
                if (i == n - 1 && dp[i][j].y > 0 || i < n - 1 && dp[i][j].y > 0 && dp[i + 1][j].y < dp[i][j].y) {
                    ans.add(dp[i][j].y);
                }
            }
            if (ans.size() != cols[j].length) return false;
            int len = ans.size();
            for (int k = 0; k < len; k++) {
                if (cols[j][k] != ans.get(len - k - 1)) {
                    return false;
                }
            }
        }
        return true;
    }
    public static void main(String[] args) {
        int[][] matrix = {
                { 1, 1, 1, 1 },
                { 0, 1, 1, 1 },
                { 0, 1, 0, 0 },
                { 1, 1, 0, 1 },
                { 0, 0, 1, 1 } };
        int[][] rows = { {}, { 1 }, { 1, 2 }, { 1 }, { 2 } };
        int[][] cols = { { 2, 1 }, { 1 }, { 2 }, { 1 } };
        System.out.println(checkNonogram(matrix, rows, cols));
    }
}
