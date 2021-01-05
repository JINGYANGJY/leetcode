package DP;

public class Leet651_4KeysKeyboard {
    public int maxA(int N) {
        /*
            press key for N times, maximum number of 'A'
            dp[i]: press i time, maximum number of 'A'
            case 1: press A
                    dp[i - 1] + 1
            case 2: print buffer
                    2 * dp[i - 3]
            dp[0] = 0;
            dp[1] = 1;
            dp[2] = 2;
            dp[3] = 3;
        */
        int[] dp = new int[N + 1];
        if (N <= 3) {
            return N;
        }
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        dp[4] = 4;
        for (int i = 5; i <= N; i++) {
            dp[i] = Math.max(dp[i - 1] + 1, Math.max(2 * dp[i - 3], Math.max(3 * dp[i - 4], 4 * dp[i - 5])));
        }
        return dp[N];
    }
}
