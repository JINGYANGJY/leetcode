package interview.hard;

public class firstMissingPositive {
    /*
    Given an unsorted integer array nums, find the smallest missing positive integer.
    Follow up: Could you implement an algorithm that runs in O(n) time and uses constant extra space.?
     */
    public int firstMissingPositive(int[] nums) {
        int pointer = 0;
        while (pointer < nums.length) {
            int cur = nums[pointer];
            while (true) {
                if (cur <= 0 || cur > nums.length || nums[cur - 1] == cur) {
                    break;
                }
                int cnt = nums[cur - 1];
                nums[cur - 1] = cur;
                cur = cnt;
            }
            pointer++;
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                res = i + 1;
                break;
            }
        }
        return res == Integer.MAX_VALUE ? nums.length + 1 : res;
    }
}
