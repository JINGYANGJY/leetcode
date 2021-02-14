package intuit.vo;

import java.util.HashMap;
import java.util.Map;

public class Leet409_LongestPalindrome {
    /*
    https://leetcode.com/problems/longest-palindrome/
     */
    public int longestPalindrome(String s) {
        Map<Character, Integer> map = new HashMap<>();
        char[] arr = s.toCharArray();
        for (char c : arr) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int res = 0;
        int odd = 0;
        for (char key : map.keySet()) {
            int n = map.get(key);
            if (n % 2 == 0) {
                res += n;
            } else {
                if (n > 2) {
                    res += n - 1;
                }
                odd++;
            }
        }
        return odd >= 1 ? res + 1 : res;
    }
}
