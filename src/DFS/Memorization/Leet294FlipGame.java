package DFS.Memorization;

import java.util.HashMap;
import java.util.Map;

public class Leet294FlipGame {
    public boolean canWin(String s) {
        int N = s.length();
        Map<String, Boolean> memo = new HashMap<>();
        char[] arr = s.toCharArray();
        return dfs(arr, memo);
    }

    private boolean dfs(char[] arr, Map<String, Boolean> memo) {
        for (int i = 0; i < arr.length - 1; i++) {
            if ((arr[i] == '+') && (arr[i + 1] == '+')) {
                arr[i] = '-';
                arr[i + 1] = '-';
                String key = new String(arr);
                Boolean tmp = memo.get(key);
                if (tmp == null) {
                    tmp = dfs(arr, memo);
                    memo.put(key, tmp);
                }
                arr[i] = '+';
                arr[i + 1] = '+';
                if (!tmp) {
                    return true;
                }
            }
        }
        return false;
    }
}
