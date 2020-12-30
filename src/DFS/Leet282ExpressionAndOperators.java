package DFS;

import java.util.ArrayList;
import java.util.List;

public class Leet282ExpressionAndOperators {
    /*
    抄答案的，得多加练习
     */
        public List<String> addOperators(String num, int target) {
            List<String> res = new ArrayList<>();
            dfs(0, num, 0, 0, "", target, res);
            return res;
        }
        private void dfs(int level, String num, long prev, long product, String cnt, int target, List<String> res) {
            if (level == num.length()) {
                if (prev == target) {
                    res.add(cnt);
                }
                return;
            }
            for (int i = level; i < num.length(); i++) {
                if (i != level && num.charAt(level) == '0') {
                    break;
                }
                long cur = Long.valueOf(num.substring(level, i + 1));
                if (level == 0) {
                    dfs(i + 1, num, cur, cur, cnt + String.valueOf(cur), target, res);
                } else {
                    dfs(i + 1, num, prev + cur, cur, cnt + "+" + String.valueOf(cur), target, res);
                    dfs(i + 1, num, prev - cur, -cur, cnt + "-" + String.valueOf(cur), target, res);
                    dfs(i + 1, num, prev - product + cur * product, product * cur, cnt + "*" + String.valueOf(cur), target, res);
                }

            }
        }
}
