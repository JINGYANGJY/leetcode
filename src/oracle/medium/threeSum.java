package oracle.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class threeSum {
    /*
    Given an array nums of n integers,
    are there elements a, b, c in nums such that a + b + c = 0?
    Find all unique triplets in the array which gives the sum of zero.
    Notice that the solution set must not contain duplicate triplets.
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        /*
        Clarification:
            Are the nums sorted or not?
            [-1,0,1,2,-1,-4]
            -4 -1 -1 0 1 2

            [-1, -1, 0, 0, 1, 1]
            [-1, 0, 1]? or [-1, 0, 1] [-1, 0, 1]...
            BF:
                all combinations
                O(n^3)
            optimization：
                1. sorted the nums
                2. for each unique num
                        use two pointer to find if there are two nums sum == 0 - num

              Time:  O(n^2)
              Space: O(1)
         */
            List<List<Integer>> res = new ArrayList<>();
            Arrays.sort(nums);
            for (int i = 0; i < nums.length - 2; i++) {
                int left = i + 1, right = nums.length - 1;
                while (left < right) {
                    if (nums[left] + nums[right] + nums[i] == 0) {
                        res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                        left++;
                        right--;
                        while (left < right && left - 1 > 0 && nums[left] == nums[left - 1]) {
                            left++;
                        }
                        while (left < right && right + 1 < nums.length && nums[right] == nums[right + 1]) {
                            right--;
                        }
                    } else if (nums[left] + nums[right] + nums[i] < 0) {
                        left++;
                    } else {
                        right--;
                    }

                }
                while (i + 1 < nums.length && nums[i] == nums[i + 1]) {
                    i++;
                }
            }
            return res;
        }

    public static void main(String[] args) {
        List<List<Integer>> res = threeSum(new int[]{-1,0,1,2,-1,-4});
        for (List<Integer> list : res) {
            for (Integer i : list) {
                System.out.print(i);
                System.out.print(" ");
            }
        }
    }
}
