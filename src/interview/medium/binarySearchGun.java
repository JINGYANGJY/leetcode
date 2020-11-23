package interview.medium;
import java.util.*;

public class binarySearchGun {
        public static int solution(int[] A, int[] B, int[] C) {
            // write your code in Java SE 8
            int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
            for (int i = 0; i < A.length; i++) {
                min = Math.min(min, A[i]);
                max = Math.max(max, B[i]);
            }
            List<Integer>[] arr = (List<Integer>[])new List[max + 1];
            for (int i = 0; i < A.length; i++) {
                for (int j = A[i]; j <= B[i]; j++) {
                    if (arr[j] == null) {
                        arr[j] = new ArrayList<>();
                    }
                    arr[j].add(i);
                }
            }
            int[] dp = new int[A.length];
            Arrays.sort(C);
            for (int i : C) {
                if (arr[i] == null) continue;
                int start = arr[i].get(0);
                int end = arr[i].get(arr[i].size() - 1);
                int index = binarySearch(dp, start - 1, start);
                int before = index == -1 ? 0 : dp[index];
                for (int j = index + 1; j <= end; j++) {
                    dp[j] = before + 1;
                }
            }
            return dp[dp.length - 1];
        }
        private static int binarySearch(int[] dp, int target, int start) {
            int res = -1;
            int left = 0, right = start;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (mid == target) {
                    return mid;
                }
                if (mid < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return res;
        }

        public static void main(String[] args) {
            int[] A = {1, 4, 5, 8};
            int[] B = {4, 5, 9, 10};
            int[] C = {4, 6, 7, 10, 2};
            System.out.println(solution(A, B, C));
        }
}
