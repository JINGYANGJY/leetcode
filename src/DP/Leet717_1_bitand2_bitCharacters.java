package DP;

public class Leet717_1_bitand2_bitCharacters {
    public boolean isOneBitCharacter(int[] bits) {
        /*
        dp[i]: start with index i, the substring(i)'s last character must be a one-bit character or not.
        dp[i]:
                bits[i] = 0
                    dp[i] = dp[i + 1]
                bits[i] = 1
                    dp[i] = dp[i + 2]

        goals to find whether dp[0] = true;
            [1, 0, 0]
                T  T
        */
        int n = bits.length;
        boolean[] dp = new boolean[n + 1];
        dp[n - 1] = true;
        for (int i = bits.length - 2; i >= 0; i--) {
            if (bits[i] == 0) {
                dp[i] = dp[i + 1];
            } else {
                if (i + 2 < n) {
                    dp[i] = dp[i + 2];
                }
            }
        }
        return dp[0];
    }
}
