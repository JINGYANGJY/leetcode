package DFS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Leet241DifferentWaysToAddParenthesis {
        public List<Integer> diffWaysToCompute(String input) {
        /*
          BF:
            DFS add parentheses at the valid position
                how many left parenthesis before
                    and at current position ')'
                    or '('
            Time: O(2^n * n)

            --> goal to find all possible result
            no need expressions
            2*3-4*5
            ()
            DFS
            for each level: choose any consecutive two operands with one operator, compute them
                        generate new string
                    following...
                    until only one operands
            DFS: n -- input.length
                 M -- operands
            Time: O(M ^ (M - 1)) --> more details need to add later

                                2*3-4*5
                                /    |.          \
                         (2*3)-4*5  2*(3-4)*5  2*3-(4*5)
                      -> 6 - 4 * 5
                         /       \
                   (6 - 4) * 5   6 - (4 * 5)
                     |              |
                     2 * 5       6 -20
                        |
                      10

            DFS + Memo
                Map<String, List<Integer>> map

        */

            Map<String, List<Integer>> res = new HashMap<>();;
            dfs(input, res);
            return res.get(input);
        }

        private List<Integer> dfs(String input, Map<String, List<Integer>> map) {
            if (map.get(input) != null) {
                return map.get(input);
            }
            List<Integer> res = new ArrayList<>();
            if (input == null) {
                return res;
            }
            for (int index = 0; index < input.length(); index++) {
                if (isOperator(input.charAt(index))) {
                    List<Integer> leftOperands = dfs(input.substring(0, index), map);
                    List<Integer> rightOperands = dfs(input.substring(index + 1), map);
                    for (Integer i : leftOperands) {
                        for (Integer j : rightOperands) {
                            res.add(compute(i, j, input.charAt(index)));
                        }
                    }
                }
            }
            if (res.size() == 0) res.add(Integer.valueOf(input));
            map.put(input, res);
            return res;
        }

        private boolean isOperator(char c) {
            return c == '+' || c == '-' || c == '*';
        }

        private int compute(int i, int j, char c) {
            if (c == '+') {
                return i + j;
            } else if (c == '-') {
                return i - j;
            } else {
                return i * j;
            }
        }
}
