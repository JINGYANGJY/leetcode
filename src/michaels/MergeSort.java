package michaels;

import java.util.Arrays;
import java.util.List;

public class MergeSort {
    public static int[] mergeSort(int[] nums) {
        /*
            int[]
                9   8   7   6   5   4   3   2
            recursion
                      left          right
                     /    \
                   left   right
                  9  8
                 /   /
                9    8
             step 1:
                    divide the array, until each sub array only has one elements
             step 2:
                    merge

                    two pointers
                    9
                    p

                    8
                        q
                    [8, 9]     [6, 7]
                    [6, 7, 8, 9]   [2, 3, 4, 5]
                    [1, 2, 3, 4, 5, 6, 7, 8]
            Time: O(nlogn)
            Space: O(nlogn)
         */

        return mergeSort(nums, 0, nums.length - 1);
    }

    private static int[] mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            return nums;
        }
        int mid = left  + (right - left) / 2;
        int[] leftPart = mergeSort(nums, left, mid);
        int[] rightPart = mergeSort(nums, mid + 1, right);
        return merge(nums, left, mid, leftPart, mid + 1, right, rightPart);
    }

    private static int[] merge(int[] nums, int l1, int r1, int[] leftPart, int l2, int r2, int[] rightPart) {
        int[] res = Arrays.copyOf(nums, nums.length);
        int index = l1 < l2 ? l1 : l2;
        while (l1 <= r1 || l2 <= r2) {
            Integer m1 = l1 <= r1 ? leftPart[l1] : null;
            Integer m2 = l2 <= r2 ? rightPart[l2] : null;
            if (m1 != null && m2 != null && m1 <= m2 || m2 == null && m1 != null) {
                res[index++] = m1;
                l1++;
            } else {
                res[index++] = m2;
                l2++;
            }
        }
        return res;
    }

    public  static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] n1 = {8, 7, 6, 5, 4, 3, 9};
        mergeSort2(n1, 0, n1.length - 1);
        System.out.println(Arrays.toString(n1));
    }

    private static void mergeSort2(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort2(nums, left, mid);
        mergeSort2(nums, mid + 1, right);
        merge(nums, left, right);
    }

    private static void merge(int[] nums, int left, int right) {
        int[] arr = Arrays.copyOf(nums, nums.length);
        int mid = left + (right - left) / 2;
        int p = left;
        int q = mid + 1;
        int index = left;
        while (p <= mid || q <= right) {
            Integer m1 = p <= mid ? nums[p] : null;
            Integer m2 = q <= right ? nums[q] : null;
            if (m1 != null && m2 != null && m1 <= m2 || m2 == null) {
                arr[index++] = m1;
                p++;
            } else {
                arr[index++] = m2;
                q++;
            }
        }
        for (int i = 0; i < arr.length; i++) {
            nums[i] = arr[i];
        }
    }
}
