package DP;

public class Leet53MaximumSubArray {
    public int maxSubArray(int[] nums) {
        /*
        Clarification:
            negative number? yes
            dp[i]: end with index i, the maximum subarray
            dp[i] =  dp[i - 1] < 0 ? nums[i] : dp[i - 1] + nums[i];
            base case: dp[0] = nums[0]
        */

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = dp[i - 1] < 0 ? nums[i] : dp[i - 1] + nums[i];
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
