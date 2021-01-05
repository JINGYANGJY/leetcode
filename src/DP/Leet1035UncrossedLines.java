package DP;

public class Leet1035UncrossedLines {
    public int maxUncrossedLines(int[] A, int[] B) {
        /*
        maximum number of connecting lines
        --> longest common subsequence of A and B

        dp[i][j]: end with index i of A, and index j of B, the longest common subsequence of A and B
        A[i] == B[j]
        dp[i][j] = dp[i - 1][j - 1] + 1
        A[i] != B[j]
        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1])
        dp[0][0] = A[0] == B[0] = 1 : 0;

        O(m * n)
        n = A.length
        m = B.length
        */
        int res = 0;
        int n = A.length;
        int m = B.length;
        int[][] dp = new int[n][m];
        dp[0][0] = A[0] == B[0] ? 1 : 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0) {
                    dp[0][0] = A[0] == B[0] ? 1 : 0;
                } else if (i == 0) {
                    dp[0][j] = A[0] == B[j] ? 1 : dp[0][j - 1];
                } else if (j == 0) {
                    dp[i][0] = A[i] == B[0] ? 1 : dp[i - 1][0];
                } else {
                    if (A[i] == B[j]) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j - 1], Math.max(dp[i - 1][j], dp[i][j - 1]));
                    }
                }
                res = Math.max(res, dp[i][j]);
            }
        }
        return res;
    }
}
