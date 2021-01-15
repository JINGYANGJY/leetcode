package amazon.DFS;

import java.util.*;

public class Leet472_ConcatenatedWords {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        /*
            for each word
                check if it is a Concatenated word
                -> dp
                -> dp[i]: represents ending with index i of the word, if this is a concatenated word
                   induction rule:dp[j] & substring(j + 1, i + 1) exists in the words
                                  j is in range from [0, i - 1]
                   base case: dp[0] = true
                -> check in the words -> set -> O(1)
            Time:
                O(n * |word.length| ^ 2)
        */
        List<String> res = new ArrayList<>();
        Set<String> set = new HashSet<>(Arrays.asList(words));
        for (String word : words) {
            if (word.length() > 0 && isConcatenatedWord(set, word)) {
                res.add(word);
            }
        }
        return res;
    }

    private boolean isConcatenatedWord(Set<String> words, String word) {
        words.remove(word);
        int n = word.length();
        char[] arr = word.toCharArray();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && words.contains(new String(arr, j, i - j))) {
                    dp[i] |= true;
                    break;
                }
            }
        }
        words.add(word);
        return dp[n];
    }
}
