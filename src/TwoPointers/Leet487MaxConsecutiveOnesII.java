package TwoPointers;

public class Leet487MaxConsecutiveOnesII {
    public int findMaxConsecutiveOnes(int[] nums) {
        /*
        ---> longest 1s which contains at most one 0
        --->    sliding window
                fast: tranverse the nums
                slow: the leftmost boundary for subarray [slow, fast] longest 1s which contains at most one 0

            --> maintains number of 0 within [slow, fast]
                int count
                for (fast [0, nums.length - 1])
                    if 0, count++
                    while (0 > 1) {
                        move slow
                    }
                 update the global result
            Time: O(n) n is the length of nums

        */

        int count = 0;
        int res = 0;
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] == 0) {
                count++;
            }
            while (slow < fast && count > 1) {
                if (nums[slow] == 0) {
                    count--;
                }
                slow++;
            }
            res = Math.max(res, fast - slow + 1);
        }
        return res;
    }
}
