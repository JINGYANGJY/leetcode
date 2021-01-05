package weeklyContest;
/*
https://leetcode.com/problems/ways-to-split-array-into-three-subarrays/
 */
public class Leet1712_WaystoSplitArrayIntoThreeSubarrays {
    int mod = (int)Math.pow(10, 9) + 7;
    public int waysToSplit(int[] nums) {
        int[] prefixSum = new int[nums.length];
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            prefixSum[i] = sum;
        }
        int res = 0;
        for (int i = 1; i < nums.length - 1; i++) {
            int leftmost = binarySearchLeft(i, nums.length - 2, prefixSum, nums, prefixSum[i - 1] - prefixSum[0] + nums[0]);
            int rightmost = binarySearchRight(i, nums.length - 1, prefixSum, nums);
            if (leftmost == -1 || rightmost == -1) {
                continue;
            }
            if (rightmost >= leftmost) {
                res = (((res + rightmost) % mod - leftmost + mod) % mod + 1) % mod;
            }
        }
        return res;
    }

    private int binarySearchLeft(int left, int right, int[] prefix, int[] nums, int target) {
        int res = -1;
        int l = left;
        int r = right;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int sum = prefix[mid] - prefix[left] + nums[left];
            if (sum >= target) {
                res = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return res;
    }

    private int binarySearchRight(int left, int right, int[] prefix, int[] nums) {
        int l = left;
        int r = right;
        int res = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (mid == right) break;
            int middleVal = prefix[mid] - prefix[left] + nums[left];
            int rightVal = prefix[right] - prefix[mid + 1] + nums[mid + 1];
            if (middleVal <= rightVal) {
                res = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return res;
    }
}
