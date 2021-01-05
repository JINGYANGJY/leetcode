package DP;

public class Leet300LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        /*
        -->
            dp[i]: end with index i, the length of the longest strictly increasing subsequence.
                dp[j] which nums[j] < nums[i]

            dp[i] =
                for (j) nums[j] < nums[i]
                    max over {dp[j] + 1}
            dp[0] = 1

            Time:O(n^2)
        */
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int res = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
