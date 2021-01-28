package amazon.TwoPointers.normal;

import java.util.Arrays;

public class Leet532_K_diffPairsinanArray {
    public int findPairs(int[] nums, int k) {
        /*
        k-diff pairs
        sort the nums by increasing order
            ? if not sort, enumberate all pairs n^2 and check unique pair O(n)
        two pointers
            for each unique element nums[i], find if exists nums[j], which satisfy requirement

            e.g. [3,1,4,1,5]
            [1, 1, 3, 4, 5]
                 slow
                        fast
            Time:O(nlogn)
            for each unique fast, find the unique slow
        */
        Arrays.sort(nums);
        int res = 0;
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (fast + 1 < nums.length && nums[fast + 1] == nums[fast]) {
                continue;
            }
            while (nums[fast] - nums[slow] > k && slow < fast) {
                slow++;
            }
            if (slow != fast && nums[fast] - nums[slow] == k) {
                res++;
            }
            while (slow + 1 <= fast && nums[slow + 1] == nums[slow]) {
                slow++;
            }
        }
        return res;
    }
}
