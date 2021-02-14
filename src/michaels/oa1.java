package michaels;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class oa1 {
    public static long numberOfX(long l, int p, int k, long r) {
        int minLen = String.valueOf(l).length();
        int maxLen = String.valueOf(r).length();
        long res = 0;
        for (int i = Math.max(minLen, p); i <= maxLen; i++) {
            List<char[]> list = new ArrayList<>();
            dfs(new char[i], i, p, k, list);
            res += getNumber(list, l, r);
        }
        return res;
    }

    private static long getNumber(List<char[]> list, long l, long r) {
        char[] left = String.valueOf(l).toCharArray();
        char[] right = String.valueOf(r).toCharArray();
        long res = 0;
        for (char[] chars : list) {
            res += combinations(left, chars, right);
        }
        return 0;
    }

    private static long combinations(char[] left, char[] chars, char[] right) {
        int len = chars.length;
        long res = 1;
        long ans = 1;
        long min = 0;
        long max = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ' ') {
                int l = left[left.length - len + i];
                int r = right[right.length - len + i];
                min = min * 10 + left[l] - '0';
                 max = max * 10 + right[r] - '0';
            } else {
                if (max < min) continue;
                if (chars[i] > left[left.length - len + i]) {
                    ans *= (max - min) - 1;
                } else {
                    ans *= (max - min);
                }
                min = 0;
                max = 0;
            }
            res += ans;
        }
        return res;
    }

    private static void dfs(char[] res, int len, int p, int k, List<char[]> list) {
        if (p == 0 && len == 0) {
            list.add(Arrays.copyOf(res, res.length));
            return;
        }
        if (len <= 0) {
            return;
        }
        res[len - 1] = (char)('0' + k);
        dfs(res, len - 1, p - 1, k, list);
        res[len - 1] = ' ';
        dfs(res, len - 1, p, k, list);
    }

    public static void main(String[] args) {
        System.out.println(numberOfX(40000,2, 1, 50000000));
    }
}
