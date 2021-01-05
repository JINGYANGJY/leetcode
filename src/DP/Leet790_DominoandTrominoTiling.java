package DP;

public class Leet790_DominoandTrominoTiling {
    long MOD = (long)(Math.pow(10, 9) + 7);
    public int numTilings(int N) {
        /*
        dp[i][0]: represents how many ways to tile a 2 * i board was filled,
        dp[i][1]: represents how many ways to tile 1 2 * i
                                                            * *
                                                            *
        dp[i][2]
                                                            *
                                                            * *
        dp[i][0] = dp[i - 1][0] + dp[i - 2][0]
        dp[i][1] = dp[i - 2][0] + dp[i - 1][2]
        dp[i][2] = dp[i - 2][0] + dp[i - 1][1]
        */

        long[][] dp = new long[N + 1][3];
        dp[0][0] = 1;
        dp[1][0] = 1;
        for (int i = 2; i <= N; i++) {
            dp[i][0] = ((dp[i - 1][0] + dp[i - 2][0]) % MOD + (dp[i - 1][1] + dp[i - 1][2]) % MOD) % MOD;
            dp[i][1] = (dp[i - 2][0] + dp[i - 1][2]) % MOD;
            dp[i][2] = (dp[i - 2][0] + dp[i - 1][1]) % MOD;
        }
        return (int)dp[N][0];
    }
}
