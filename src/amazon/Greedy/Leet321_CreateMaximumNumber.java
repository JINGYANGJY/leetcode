package amazon.Greedy;

import java.util.ArrayDeque;
import java.util.Deque;

public class Leet321_CreateMaximumNumber {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int[] max = new int[k];
        for (int i = 0; i <= k; i++) {
            if (nums1.length >= i && nums2.length >= k - i) {
                int[] maxNumber1 = getMaxNumberFromArray(nums1, i);
                int[] maxNumber2 = getMaxNumberFromArray(nums2, k - i);
                int[] maxNumber = buildNumber(maxNumber1, maxNumber2, k);
                max = maxOne(max, maxNumber);
            }
        }
        return max;
    }

    private int[] maxOne(int[] max, int[] maxNumber) {
        for (int i = 0; i < max.length; i++) {
            if (max[i] > maxNumber[i]) {
                return max;
            } else if (max[i] < maxNumber[i]) {
                return maxNumber;
            }
        }
        return max;
    }

    private int[] getMaxNumberFromArray(int[] nums, int k) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && stack.peek() < nums[i] && stack.size() + nums.length - i  > k) {
                stack.pop();
            }
            stack.push(nums[i]);
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = stack.pollLast();
        }
        return res;
    }

    private int[] buildNumber(int[] nums1, int[] nums2, int k) {
        int[] res = new int[k];
        int p = 0, q = 0;
        int index = 0;
        while (p < nums1.length || q < nums2.length) {
            int num1 = p >= nums1.length ? -1 : nums1[p];
            int num2 = q >= nums2.length ? -1 : nums2[q];
            if (num1 < num2) {
                res[index++] = num2;
                q++;
            } else if (num1 > num2) {
                res[index++] = num1;
                p++;
            } else {
                int nextp = p;
                int nextq = q;
                boolean movep = true;
                while (nextp < nums1.length && nextq < nums2.length) {
                    if (nums1[nextp] == nums2[nextq]) {
                        nextp++;
                        nextq++;
                    } else if (nums1[nextp] > nums2[nextq]) {
                        break;
                    } else {
                        movep = false;
                        break;
                    }
                }
                if (movep && p + 1 < nums1.length) {
                    res[index++] = num1;
                    p++;
                } else {
                    res[index++] = num2;
                    q++;
                }
            }
        }
        return res;
    }
}
