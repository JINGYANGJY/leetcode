package DP;

public class Leet361_BombEnemy {
    public int maxKilledEnemies(char[][] grid) {
        /*
        for each cell <i, j>
            if <i, j> is 0
                get how many E in a row and a column
        n == grid.length
        m == grid[0].length
        Time: O(n * m * max(n, m))

        ------------------
        optimizations:
        Time: O(n * m * max(n, m)) --> O(n  * m)
        left[i][j]: from left to right, ends with index j, for row i how many E
        right[i][j]: ...right to left, ends with index j,.....
        top[i][j]: ....top to bottom, ends with index i, for column j how many Es
        bottom[i][j]: ...bottom to top, ends with index i, for column j, .....

        left[i][j]
            for each i
                if grid[i][j] = E: count++
                if grid[i][j] = W: count = 0;
                if grid[i][j] = 0 left[i][j] = count
        ...
        global max
        dp[i][j]
            if grid[i][j] == 0
            left[i][j] + right[i][j] + top[i][j] + bottom[i][j]
            update globalMax
        */
        if (grid.length == 0) {
            return 0;
        }
        int res = 0;
        int n = grid.length;
        int m = grid[0].length;
        int[][] left = calculateFromLeft(grid);
        int[][] right = calculateFromRight(grid);
        int[][] top = calculateFromTop(grid);
        int[][] bottom = calculateFromBottom(grid);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '0') {
                    res = Math.max(res, left[i][j] + right[i][j] + top[i][j] + bottom[i][j]);
                }
            }
        }
        return res;
    }
    // from left to right
    private int[][] calculateFromLeft(char[][] grid) {
        int[][] left = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            int count = 0;
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '0') {
                    left[i][j] = count;
                } else if (grid[i][j] == 'E') {
                    count++;
                } else if (grid[i][j] == 'W') {
                    count = 0;
                }
            }
        }
        return left;
    }
    // from right to left
    private int[][] calculateFromRight(char[][] grid) {
        int[][] right = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            int count = 0;
            for (int j = grid[0].length - 1; j >= 0; j--) {
                if (grid[i][j] == '0') {
                    right[i][j] = count;
                } else if (grid[i][j] == 'E') {
                    count++;
                } else if (grid[i][j] == 'W') {
                    count = 0;
                }
            }
        }
        return right;
    }
    // from top to bottom
    private int[][] calculateFromTop(char[][] grid) {
        int[][] top = new int[grid.length][grid[0].length];
        for (int j = 0; j < grid[0].length; j++) {
            int count = 0;
            for (int i = 0; i < grid.length; i++) {
                if (grid[i][j] == '0') {
                    top[i][j] = count;
                } else if (grid[i][j] == 'E') {
                    count++;
                } else if (grid[i][j] == 'W') {
                    count = 0;
                }
            }
        }
        return top;
    }
    //from bottom to top
    private int[][] calculateFromBottom(char[][] grid) {
        int[][] bottom = new int[grid.length][grid[0].length];
        for (int j = 0; j < grid[0].length; j++) {
            int count = 0;
            for (int i = grid.length - 1; i >= 0; i--) {
                if (grid[i][j] == '0') {
                    bottom[i][j] = count;
                } else if (grid[i][j] == 'E') {
                    count++;
                } else if (grid[i][j] == 'W') {
                    count = 0;
                }
            }
        }
        return bottom;
    }
}
