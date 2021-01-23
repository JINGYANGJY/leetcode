package amazon.String;

import java.util.HashMap;
import java.util.Map;

public class Leet76_MinimumWindowSubstring {
    public static String minWindow(String s, String t) {
        /*
        s = "ADOBECODEBANC", t = "ABC"
        "BANC"
        -   enumerate all substring
            - check if has all characters in t
               how?
                    - characters and frequency in t
                    - characters and frequency in substring
            Time: O(n^2 * n * |distinct characters in the substring|)
        ----------------------------------------------------------------
        optimizations
            "A D O B E C O D E B A N C"
                               s
                                     f
            A: 1 ...B:1 C:1
            cnt : "ADOBEC", "CODEBA" "BANC"
             Map<Character, Integer> windowMap
             window [s, f] contains all characters in the t

            - characters and frequency in t
              Map<Character, Integer> map
              <A, 1>
              <B, 1>
              <C, 1>
           shortestLen
           startIndex
           f: traverse the s

           s: the leftmost boundary for window [s, f] to match all characters in the t
               if [s, f] match t
                try to move s to shrink the window size
           Map<Character, Integer> targetMap
           Map<Character, Integer> sourceMap [s, f]
           Time: O(n * compare(map))
           -> compare(map) -> O(1)
           numOfMatch == targetMap.size()
                update cnt
           numOfMatch = 0:
                if (targetMap.get(c) == windowMap.get(c)) numOfMatch++

        */
        if (t.length() > s.length()) {
            return "";
        }
        Map<Character, Integer> targetMap = new HashMap<>();
        for(char c : t.toCharArray()) {
            targetMap.put(c, targetMap.getOrDefault(c, 0) + 1);
        }
        int startIndex = -1;
        int shortestLen = Integer.MAX_VALUE;
        Map<Character, Integer> windowMap = new HashMap<>();
        int slow = 0;
        char[] arr = s.toCharArray();
        int numOfMatch = 0;
        for (int fast = 0; fast < arr.length; fast++) {
            windowMap.put(arr[fast], windowMap.getOrDefault(arr[fast], 0) + 1);
            if (windowMap.get(arr[fast]) == targetMap.get(arr[fast])) {
                numOfMatch++;
            }
            while (numOfMatch == targetMap.size() && slow < fast && (!targetMap.containsKey(arr[slow]) || windowMap.containsKey(arr[slow]) &&  targetMap.containsKey(arr[slow]) && windowMap.get(arr[slow]) > targetMap.get(arr[slow]))) {
                Integer count = windowMap.get(arr[slow]);
                if (count == 1) {
                    windowMap.remove(arr[slow]);
                } else {
                    windowMap.put(arr[slow], count - 1);
                }
                slow++;
            }
            if (numOfMatch == targetMap.size()) {
                if (fast - slow + 1 < shortestLen) {
                    startIndex = slow;
                    shortestLen = fast - slow + 1;
                }
            }
        }
        System.out.println(startIndex);
        System.out.println(shortestLen);
        return startIndex == -1 ? "" : s.substring(startIndex, startIndex + shortestLen);
    }
}
