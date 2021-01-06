package DFS.Memorization;

public class Leet375_GuessNumberHigherorLowerII {
    public int calculate(int low, int high, int[][] memo) {
        if (low >= high)
            return 0;
        if (memo[low][high] > 0) {
            return memo[low][high];
        }
        int minres = Integer.MAX_VALUE;
        for (int i = low; i <= high; i++) {
            int res = i + Math.max(calculate(i + 1, high, memo), calculate(low, i - 1, memo));
            minres = Math.min(res, minres);
        }
        memo[low][high] = minres;
        return minres;
    }
    public int getMoneyAmount(int n) {
        int[][] memo = new int[n + 1][n + 1];
        return calculate(1, n, memo);
    }
}
