package intuit.vo;

import java.util.Arrays;
import java.util.TreeSet;

public class SmallestLargerElementOnTheRightSide {
    /*
    eg1
      1 2 3 4 5
      2  3  4  5  -1
   eg2
        5 4 3 2  1
        -1 -1 -1 -1 ..
   eg3

           9 5 8 3  4  1
           -1  8  -1  4  -1  -1

      smallest larger element on the right side
      start from the end
            for each element
                find smallest larger element
      -> maintain all elements on its right side
      -> sorted
         TreeSet
            - add logn
            - find smallest larger logn
     */

    public static int[] smallestLarger(int[] nums) {
        TreeSet<Integer> set = new TreeSet<>();
        int[] res = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            Integer smallestLarger = set.ceiling(nums[i]);
            if (smallestLarger == null) {
                res[i] = -1;
            } else {
                res[i]  = smallestLarger;
            }
            set.add(nums[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        int[] nums1 = {5, 4, 3, 2, 1};
        int[] nums2 = {9, 5, 8, 3, 4, 1};
        System.out.println(Arrays.toString(smallestLarger(nums)));
        System.out.println(Arrays.toString(smallestLarger(nums1)));
        System.out.println(Arrays.toString(smallestLarger(nums2)));
    }
}
