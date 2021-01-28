package amazon.Array;

public class Leet918_MaximumSumCircularSubarray {
    public int maxSubarraySumCircular(int[] A) {
        /*
        - no circular
            [5, -3, 5]
            maximum sum of a subarray
            dp[i]: ending at current position, the maximum sum of a subarry
            dp[i] = dp[i - 1] + A[i] > A[i] ? dp[i - 1] + A[i] : A[i]
        - circular
            if has circle,
                the circle should be
                    the left part of A and right part of B
                    [5, || -3, ||  5]
                -> change direction
                    if we know no circular the minimal subarray
                    sum - minimal subarray sum -> circular subarray sum
         Summary:
            1. no circular max subarray sum
            2. no circular min subarray sum
                -> dp[i]: ending at current position, the minimal sum of a subarry
                   dp[i] = dp[i - 1] + A[i] < A[i] ? dp[i - 1] + A[i] : A[i]
            -> circular sub array sum
                = max over (1, sum - 2)
        */
        if (A.length < 0) {
            return 0;
        }
        int n = A.length;
        int sum = 0;
        int[] maxDP = new int[n];
        int[] minDP = new int[n];
        maxDP[0] = A[0];
        minDP[0] = A[0];
        sum = A[0];
        int max = A[0];
        int min = A[0];
        for (int i = 1; i < n; i++) {
            sum += A[i];
            maxDP[i] = maxDP[i - 1] + A[i] > A[i] ? maxDP[i - 1] + A[i] : A[i];
            max = Math.max(max, maxDP[i]);
            minDP[i] = minDP[i - 1] + A[i] < A[i] ? minDP[i - 1] + A[i] : A[i];
            min = Math.min(min, minDP[i]);
        }

        return Math.max(max, sum != min ? sum - min : Integer.MIN_VALUE);
    }
}
