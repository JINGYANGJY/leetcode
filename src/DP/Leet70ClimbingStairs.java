package DP;

public class Leet70ClimbingStairs {
    public int climbStairs(int n) {
        /*
        Given n
        1
        2
        how many distint ways?
            dp[i]: represents for i steps, how many distinct ways
            dp[i] = dp[i - 1] + dp[i - 2];
            base case:
                dp[0] = 1;
                dp[1] = 1;
             Time: O(n)
        */
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
