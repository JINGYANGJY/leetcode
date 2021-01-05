package DFS.Memorization;

public class Leet329LongestIncreasingPathInAMatrix {
    public int longestIncreasingPath(int[][] matrix) {
        /*
        for each node
            find the increasing path in a matrix
                four directions
        DFS
                        <i, j>
                        /   |       |  \
                     left  right down up
                    / | | \
           Time: O(n ^ 2 * 4 ^ |m + n|)
           ---> optimizations

           DFS + memo
            dp[i][j] ->
                dp[i + 1][j]
                dp[i - 1][j]
                dp[i][j + 1]
                dp[i][j - 1]

            <i, j> matrix
              [9,9,4],
              [6,6,8],
              [2,1,1]
              add memo
            -> increasing path
            -> if dp[i][j] can inherits value form dp[i +- 1][j +- 1]
                matrix[i][j] < matrix[][]
                and path start from matrix[][] has not been used by matrix[i][j]
                为什么不需要mark visited， 因为 以当前点为起始点的最长的increasing path会被每一个<i, j> 标记的
          Time: O(mn)
        */
        if (matrix == null || matrix.length == 0) return 0;
        int N = matrix.length;
        int M = matrix[0].length;
        int[][] memo = new int[N][M];
        int res = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                res = Math.max(res, dfs(i, j, memo, matrix));
            }
        }
        return res;
    }

    int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private int dfs(int i, int j, int[][] memo, int[][] matrix) {
        if (memo[i][j] != 0) {
            return memo[i][j];
        }
        int res = 1;
        for (int[] d : directions) {
            int dx = i + d[0];
            int dy = j + d[1];
            if (dx < 0 || dy < 0 || dx == matrix.length || dy == matrix[0].length) {
                continue;
            }
            if (matrix[dx][dy] > matrix[i][j]) {
                res = Math.max(res, 1 + dfs(dx, dy, memo, matrix));
            }
        }
        memo[i][j] = res;
        return res;
    }
}
