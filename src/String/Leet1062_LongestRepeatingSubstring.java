package String;

import java.util.HashSet;

public class Leet1062_LongestRepeatingSubstring {
    public int longestRepeatingSubstring(String S) {
        /*
            goals to find the longest repeating substrings
            generate all substrings
            use hashset to check if there is a repeated substring
            Time:O(n^2)

            ------------------------------
            optimizations
            Time:O(n^2) --> O(nlogn)
            longest
            ----logn
            ---O(n) check if there is a repeated robin-karp
        */

        int left = 1;
        int right = S.length() - 1;
        char[] arr = S.toCharArray();
        int res = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (search(mid, arr)) {
                res = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return res;
    }
    private int a = 26;
    private long MOD = (long)(Math.pow(2, 24) + 7);
    private boolean search(int len, char[] arr) {
        HashSet<Long> set = new HashSet<>();
        long base = 1;
        for (int i = 0; i < len; i++) {
            base = (base * a) % MOD;
        }
        long initial = 0;
        for (int i = 0; i < len; i++) {
            initial = (initial * a + arr[i] - 'a') % MOD;
        }
        set.add(initial);
        for (int i = 1; i <= arr.length - len; i++) {
            initial = (initial * a - (arr[i - 1] - 'a') * base % MOD + MOD ) % MOD;
            initial = (initial + (arr[i + len - 1] - 'a')) % MOD;
            if (set.contains(initial)) {
                return true;
            }
            set.add(initial);
        }
        return false;
    }
}
