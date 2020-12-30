package DFS;

import java.util.ArrayList;
import java.util.List;

public class Leet425WordSquare {
        public List<List<String>> wordSquares(String[] words) {
        /*
          M == words[i].length;
          goals to find M words from input
            check if them can build word square
                if true
                    add into result
                false
                    continue
          --> M words
                --> size == M
                    DFS
                        each level: put one word in the current position
                        next level: same thing
                                    words[0]  ..words[1].... words[words.length - 1]
                                    ///||\\\     //|\\
                                 words[0]....    word[0].....
                                 ...
                                 level == M

                --> check if it can build the square
                    how to check?
                        generate strings column by column, check if it is equals to row by row
                --> terminated early
                    b a l l       第二行第一个必须和第一行的第二个相等
                    a b d d       第三行必须和第三列相等
                    l d e e       在添加到path时可以提前check， if true 就加 这样可以剪枝
                    l d e e

                --> optimizations
                    是list of string 并且是匹配的问题可以考虑Tire
                    construct Trie firstly
                    next find possible words for each line, can check from the trie
          Clarification:
          --> Assume the words are unique
          --> the length of words are same
          N == words.length
        Time: O(N * N-1 * ... N-(M-1) * M^2)
        */
            List<List<String>> res = new ArrayList<>();
            //corner case
            if (words == null || words.length == 0) return res;
            int M = words[0].length();
            dfs(0, new ArrayList<>(), words, M, res);
            return res;
        }
        // size == M permutations
        private void dfs(int level, List<String> path, String[] words, int M, List<List<String>> res) {
            if (level == M) {
                res.add(new ArrayList<>(path));
                return;
            }

            for (int i = 0; i < words.length; i++) {
                if (isValid(words[i], path)) {
                    path.add(words[i]);
                    dfs(level + 1, path, words, M, res);
                    path.remove(path.size() - 1);
                }
            }
        }

        // check can build the square or not
        private boolean isValid(String s, List<String> partial) {
            int row = partial.size();
            for (int i = 0; i < row; i++) {
                if (s.charAt(i) != partial.get(i).charAt(row)) {
                    return false;
                }
            }
            return true;
        }

}
