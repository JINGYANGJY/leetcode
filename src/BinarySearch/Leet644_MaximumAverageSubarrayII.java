package BinarySearch;

public class Leet644_MaximumAverageSubarrayII {
    public double findMaxAverage(int[] nums, int k) {
        /*
        enumerate all subarrays len >= k
        for
            // start index
                sum = 0
            for
                // enumerate len >= k
                sum / k update
        Time: O(n^2)
---------------------------------------------------------
optimizations:
    res: double
    10^(-5)

    binary search --> possible  result
    left            right
            mid
    left   right
         mid
         left - right < 10^(-5)

         1. left min value
            right max value

         check(mid)
            sum of subarry / len >= mid
           ( n1 + n2 + n3) / 3 >= mid
           n1 - mid + n2 - mid + n3 - mid >= 0

           1. len >= k
           2. n1 - mid + n2 - mid + n3 - mid >= 0
           ---->
                subarray >= 0 len >= k

                _______|___ k_____|_____________
      minSum         preSum    curSum

      curSum - minSum >= 0

        */
        double left = nums[0]; // min value
        double right = nums[0]; // max value
        for (int i : nums) {
            left = Math.min(left, i);
            right = Math.max(right, i);
        }
        while (right - left > 0.00001) {
            double mid = (right + left) / 2;
            if (check(mid, nums, k)) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return left;
    }

    private boolean check(double mid, int[] nums, int k) {
        double minSum = 0;
        double preSum = 0;
        double curSum = 0;
        for (int i = 0; i < nums.length; i++) {
            curSum += nums[i] - mid;
            if (i >= k - 1) {
                if (curSum - minSum >= 0) {
                    return true;
                }
                preSum += nums[i - k + 1] - mid;
                minSum = Math.min(preSum, minSum);
            }
        }
        return false;
    }
}
